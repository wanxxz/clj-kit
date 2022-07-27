(ns clj-kit.server.routes
  (:require [clojure.data.json :as json]
            [io.pedestal.http.content-negotiation :as conneg]
            [io.pedestal.http.body-params :refer [body-params]]
            [clj-kit.server.handlers.index :refer [index]]
            [clj-kit.server.handlers.pathom :refer [pathom]]))

(def supported-types ["text/html" "application/edn" "application/json" "text/plain"])

(def content-neg-intc (conneg/negotiate-content supported-types))

(defn accepted-type
  [context]
  (get-in context [:request :accept :field] "text/plain"))

(defn transform-content
  [body content-type]
  (case content-type
    "text/html"        body
    "text/plain"       body
    "application/edn"  (pr-str body)
    "application/json" (json/write-str body)))

(defn coerce-to
  [response content-type]
  (-> response
      (update :body transform-content content-type)
      (assoc-in [:headers "Content-Type"] content-type)))

(def coerce-body
  {:name ::coerce-body
   :leave
   (fn [context]
     (cond-> context
       (nil? (get-in context [:response :headers "Content-Type"]))                    ;; <1>
       (update-in [:response] coerce-to (accepted-type context))))})                  ;; <2>

(def routes #{["/" :get `index]
              ["/pathom" :post [(body-params) coerce-body content-neg-intc `pathom]]})

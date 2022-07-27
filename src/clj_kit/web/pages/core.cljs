(ns clj-kit.web.pages.core
  (:require [dumdom.core :as dumdom :refer [defcomponent]]
            [bidi.bidi :as bidi]
            [accountant.core :as accountant]
            [clerk.core :as clerk]
            [clj-kit.web.pages.index :refer [index]]))

(defonce session (atom {:route {:handler :index}}))

(def routes
  ["/" {"" :index}])

(defmulti handlers identity)
(defmethod handlers :index [] index)

(defcomponent root
  [session]
  [:div
   [:a {:href (bidi/path-for routes :index)} "/"]
   [(handlers (-> session :route :handler))]])

(defn render
  [props]
  (dumdom/render [root props] (js/document.getElementById "root")))

(defn start
  {:dev/after-load true}
  []
  (clerk/initialize!)
  (accountant/configure-navigation!
   {:nav-handler (fn
                   [path]
                   ;; (clerk/after-render!)
                   (let [match (bidi/match-route routes path)
                         handler (:handler match)
                         route-params (:route-params match)]
                     (swap! session assoc :route {:handler handler
                                                  :route-params route-params}))
                   (clerk/navigate-page! path))
    :path-exists? (fn [path]
                    (boolean (bidi/match-route routes path)))})
  (accountant/dispatch-current!)
  (add-watch session :render #(render %4))
  (render @session))

(defn after-load
  []
  (set! dumdom.component/*render-eagerly?* true))

(defn init
  []
  (start))

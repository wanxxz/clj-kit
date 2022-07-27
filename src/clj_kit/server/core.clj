(ns clj-kit.server.core
  (:require [io.pedestal.http :as http]
            [mount.core :refer [defstate args]]
            [clj-kit.server.service :refer [service]]))

(defstate dev? :start (-> (args) :dev?))

(defstate server
  :start (-> service
             (merge {:env (if dev? :dev :prod)
                     ::http/join? false
                     ::http/allowed-origins {:creds true :allowed-origins (constantly dev?)}})
             (cond-> dev? (assoc-in [::http/secure-headers
                                     :content-security-policy-settings
                                     :script-src]
                                    "'unsafe-eval' http:"))
             http/default-interceptors
             (cond-> dev? http/dev-interceptors)
             http/create-server
             http/start)
  :stop (some-> server http/stop))

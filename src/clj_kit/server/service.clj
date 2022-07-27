(ns clj-kit.server.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [mount.core :refer [defstate]]
            [clj-kit.conf :refer [conf]]
            [clj-kit.server.routes :refer [routes]]))

(defstate service :start {:env                 :prod
                          ::http/routes        (route/expand-routes routes)
                          ::http/resource-path (-> conf :server :public)
                          ::http/type          :jetty
                          ::http/port          (-> conf :server :port)})

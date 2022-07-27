(ns clj-kit.main
  (:gen-class)
  (:require [mount.core :as mount]
            [clj-kit.server.core :refer [server]]))

(defn dev
  [& args]
  (-> (mount/with-args {:dev? true})
      (mount/start #'server)))

(defn -main
  [& args]
  (mount/start #'server))

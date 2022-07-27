(ns clj-kit.server.database.core
  (:require [mount.core :refer [defstate]]
            [datalevin.core :as d]
            [clj-kit.server.database.schema :refer [schema]]
            [clj-kit.conf :refer [conf]]))

(defstate conn
  :start (-> conf :database :path (d/get-conn schema))
  :stop (d/close conn))

(ns clj-kit.conf
  (:require [cprop.core :refer [load-config]]
            [mount.core :refer [defstate]]))

(defstate conf :start (load-config :resource "conf.edn" :file "conf.edn"))

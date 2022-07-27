(ns clj-kit.server.resolvers.core
  (:require [com.wsscode.pathom3.connect.operation :as pco]
            [datalevin.core :as d]
            [clj-kit.server.database.core :refer [conn]]))

(pco/defresolver foo
  []
  {:foo (d/q '[:find (pull ?e [*])
               :where [?e _ _]]
             (d/db conn))})

(def resolvers [foo])

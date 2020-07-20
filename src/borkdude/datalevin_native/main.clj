(ns borkdude.datalevin-native.main
  (:require [datalevin.core :as d])
  (:gen-class))

(def schema {:aka  {:db/cardinality :db.cardinality/many}
             ;; :db/valueType is optional, if unspecified, the attribute will be
             ;; treated as EDN blobs
             :name {:db/valueType :db.type/string
                    :db/unique    :db.unique/identity}})

(defprotocol IFoo
  (store [_]))

(deftype Foo [^:volatile-mutable ^long max-gt]
  IFoo
  (store [_]
    (locking max-gt
      (println "Stored!"))))

(defn -main [& _args]
  (let [conn (d/create-conn schema "/tmp/datalevin-test")]
    (try
      (d/transact! conn
                   [{:name "Frege", :db/id -1, :nation "France", :aka ["foo" "fred"]}
                    {:name "Peirce", :db/id -2, :nation "france"}
                    {:name "De Morgan", :db/id -3, :nation "English"}])
      (prn (d/q '[:find ?nation
                  :in $ ?alias
                  :where
                  [?e :aka ?alias]
                  [?e :nation ?nation]]
                @conn
                "fred"))
      (finally
        (d/close conn)))))

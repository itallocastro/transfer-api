(ns transfer-api.handlers
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [transfer-api.transfer.database :as d])
  (:gen-class))

(defn pix-transfer-handler
  [req]
  (let [test (:body req)])
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->>
             (pp/pprint test)
             (str "Request Object: " test))})

(defn ted-transfer-handler
  [req]
  (let [test (:body req)])
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->>
             (pp/pprint test)
             (str "Request Object: " test))})
(ns transfer-api.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as mj]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [transfer-api.handlers :refer :all]
            [transfer-api.transfer.database :as d])
  (:gen-class))

(defroutes app-routes
  (POST "/pix" [] pix-transfer-handler)
  (POST "/ted" [] ted-transfer-handler))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [port 3000]
    (server/run-server  (wrap-defaults #'app-routes api-defaults)  {:port port})
    (println (str "Running service on port " port))))
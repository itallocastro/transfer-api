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

(defn create-user-handler [req]
  (let [user_json (:body req)
        saved (try
                (d/create-user user_json)
                (d/create-account user_json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))
        ]
    (log/info user_json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error or saving tweet")}))

(defn create-pix-key-handler [req]
  (let [pix_json (:body req)
        saved (try
                (d/create-pix-key pix_json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))
        ]
    (log/info pix_json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error or saving tweet")}))

(defn pix-transfer-handler [req]
  (let [pix_json (:body req)
        from_account (d/search-account-by-user-id pix_json)
        recipient_account (d/search-account-by-pix-key pix_json)
        saved (try
                (d/update-balance (get from_account 0) (get recipient_account 0) (get pix_json :value))
                (catch Exception e
                  (do
                    (log/error e)
                    false)))
        ]
    (log/info pix_json)
    (log/info (get from_account 0))
    (log/info recipient_account)
    (log/info (get pix_json "value"))
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error or saving tweet")}))

(defn ted-transfer-handler
  [req]
  (let [test (:body req)])
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->>
             (pp/pprint test)
             (str "Request Object: " test))})
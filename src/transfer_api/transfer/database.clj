(ns transfer-api.transfer.database
  (:require [transfer-api.db.db :refer :all]
            [cheshire.core :as json]
            [transfer-api.transfer.validation :as v]
            [clojure.tools.logging :as log]
            )
  (:import java.util.UUID)
  (:gen-class))

(defn create-user
  "create user"
  [user]
  (sql-insert-user db user))
(defn create-account
  "create account"
  [account]
  (sql-insert-account db account))

(defn create-pix-key
  "create pix key"
  [pix]
  (sql-insert-pix-key db (assoc pix :id(java.util.UUID/randomUUID))))

(defn search-account-by-user-id
  "search account by user_id"
  [pix_json]
  (sql-search-account-by-user_id db pix_json))

(defn search-account-by-pix-key
  "search account by pix key"
  [pix_json]
  (sql-search-pix-keys-recipient db pix_json))

(defn update-balance
  "search account by pix key"
  [from_account recipient_account value]
  (let [is-valid (v/validate-balance (get from_account :balance) value)]
    (log/info is-valid)
    (log/info (- (get from_account :balance) value))
    (log/info {"balance" (- (get from_account :balance) value) "id" (get from_account :id)})
    (when is-valid
      (sql-update-account db {:balance (- (get from_account :balance) value) :id (get from_account :id)})
      (sql-update-account db {:balance (+ (get recipient_account :balance) value) :id (get recipient_account :id)})
      )))
-- A :result value of :n below will return affected rows:
-- :name sql-insert-user :! :m
-- :doc Persist a user on database
insert into users (id, name, created_at)
values ((:user_id)::uuid , :name, NOW())

-- A :result value of :n below will return affected rows:
-- :name sql-insert-account :! :m
-- :doc Persist an account on database
insert into accounts (id, balance, user_id, account_number, agency_number, created_at)
values ((:account_id)::uuid, :balance, (:user_id)::uuid, :account_number, :agency_number, NOW())

-- A :result value of :n below will return affected rows:
-- :name sql-insert-pix-key :! :m
-- :doc Persist an account on database
insert into pix_keys (id, key, account_id, created_at)
values ((:id)::uuid, :key, (:account_id)::uuid, NOW())

-- A :result value of :n below will return affected rows:
-- :name sql-update-account :! :m
-- :doc Update an account on database
update accounts set balance = :balance where id = (:id)::uuid


-- A :result value of :n below will return affected rows:
-- :name sql-search-user-by-id :! :m
-- :doc Persist an account on database
select * from users inner join accounts on users.id = accounts.user_id
where users.id = (:id)::uuid

-- A :result value of :n below will return affected rows:
-- :name sql-search-account-by-user_id :! :m
-- :doc Persist an account on database
select * from accounts
where user_id = (:user_id)::uuid

-- A :result value of :n below will return affected rows:
-- :name sql-search-pix-keys-recipient :! :m
-- :doc Persist an account on database
select * from pix_keys inner join accounts on pix_keys.account_id = accounts.id
where key = :recipient_key

-- A :result value of :n below will return affected rows:
-- :name sql-search-account-by-account_number-and-agency_number :! :m
-- :doc Persist an account on database
select * from accounts where account_number = :account_number and agency_number = :agency_number


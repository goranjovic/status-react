(ns status-im.group.db
  (:require [cljs.spec.alpha :as spec]
            [status-im.constants :refer [console-chat-id wallet-chat-id]]
            [clojure.string :as string]
            [status-im.utils.homoglyph :as utils]))

(defn not-illegal-name? [username]
  (let [username (some-> username (string/trim))]
    (and (not (utils/matches username console-chat-id))
         (not (utils/matches username wallet-chat-id)))))

(spec/def ::not-empty-string (spec/and string? not-empty))
(spec/def ::not-illegal-name not-illegal-name?)

(spec/def ::name (spec/and ::not-empty-string
                           ::not-illegal-name))
;;;; DB

;; {id (string) group (map)}
(spec/def :group/contact-groups (spec/nilable map?))
;;used during editing contact group
(spec/def :group/contact-group-id (spec/nilable string?))
;;contact group or chat group
(spec/def :group/group-type (spec/nilable keyword?))
;;used during creating or edeting contact group
(spec/def :group/new-group (spec/nilable map?))
;;used during creating or edeting contact groups
(spec/def :group/new-groups (spec/nilable vector?))
(spec/def :group/contacts-group (spec/nilable map?))
(spec/def :group/selected-contacts (spec/nilable set?))
;;list of group ids
(spec/def :group/groups-order (spec/nilable seq?))
(ns status-im.chat.new-public-chat.db
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

(spec/def ::topic (spec/and ::not-empty-string
                            ::not-illegal-name
                            (partial re-matches #"[a-z0-9\-]+")))

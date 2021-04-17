;; utilities ---------
(defn split-by-keyword
  ([line]
   (split-by-keyword line #" "))
  ([line split-keyword]
   (clojure.string/split line split-keyword)))

(defn trim [line]
  (clojure.string/trim line))

(defn parse-int [str-int]
  (Integer/parseInt str-int))
;; ---------------------

(defn ->input-model [_]
  (let [n (parse-int (read-line))
        as (map parse-int (split-by-keyword (read-line)))]
    [n as]))

(defn solve [[n as]]
  (loop [a 0 b 0 _as (reverse (sort as))]
    (if-not (zero? (count _as))
      (recur b (+ a (first _as)) (rest _as))
      (reverse (sort [a b])))))

(defn output [[alice bob]]
  (println (format "%d" (- alice bob))))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)

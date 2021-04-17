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
  (map parse-int (-> (read-line) split-by-keyword)))

(defn sum-of-digit [num]
  (loop [i num acc 0]
    (if (zero? i) acc
        (recur (int (/ i 10))
               (+ acc (rem i 10))))))

(defn solve [[N A B]]
  (filter #(<= A (sum-of-digit %) B) (range (inc N))))

(defn output [nums]
  (println (format "%d" (apply + nums))))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)

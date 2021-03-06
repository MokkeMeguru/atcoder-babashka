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

(defn combination
  ([n k]
   (cond
     (or (zero? n) (zero? k)) []
     (< n k) []
     (= n k) (mapv vector (range n) (range n))
     :else (vec (filter some? (combination n k [] 0)))))
  ([n k candidate acc]
   (cond
     (= k (count candidate)) [candidate]
     (<= n acc) nil
     :else (concat (combination n k (conj candidate acc) (inc acc))
                   (combination n k candidate (inc acc))))))
;; ---------------------

(defn ->input-model [_]
  (map parse-int (split-by-keyword (read-line))))

(defn solve [[N Y]]
  (or
   (first (for [a (range (inc N))
                b (range (- (inc N) a))
                :when (= Y (+ (* 10000 a) (* 5000 b) (* 1000 (- N a b))))]
            [a b (- N a b)]))
   [-1 -1 -1]))

(defn output [comb]
  (println (clojure.string/join " " comb)))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)

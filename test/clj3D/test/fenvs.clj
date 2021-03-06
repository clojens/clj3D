(ns clj3D.test.fenvs
  (:use
    [clj3D.fenvs]
    [clojure.test]:reload)
  (:import [com.jme3.math Vector3f]))


;;Needed for private-function testing
(def jvector (ns-resolve 'clj3D.fenvs 'jvector))


(deftest curry-test
  (let [sum2 #(+ %1 %2)]
    (is (= 3 ((curry sum2 1) 2)) "Simple currying ((sum 1) 2)")
    (is (function? (curry sum2 1)))))


(deftest chr-and-ord
  (is (= \c (chr 99)))
  (is (thrown? ClassCastException (chr \c)))
  (is (thrown? ClassCastException (chr 3.41)))
  (is (= 99 (ord \c)))
  (is (thrown? ClassCastException (ord 9.9)))
  (is (thrown? ClassCastException (ord "a")))
  (is (= \c (chr (ord \c))))
  (is (= 99 (ord (chr 99)))))


(deftest cat-test
  (is (= [] (cat [])))
  (is (= [1 2 3]) (cat [1 2 3]))
  (is (= [1 2 3 4 5 6]) (cat [1 2 3] [4 5 6]))
  (is (= [1 2 3 4]) (cat [1 2] '(3 4)))
  (is (= [1 2 3 4 5 6]) (cat [1 2] '(3 4) [5 6]))
  (is (= [1 2 3 4 8 8]) (cat [1 2] '(3 4) '(8 8)))
  (is (= [1 2 3 4 8 8 9 8]) (cat [1 2] '(3 4) [8 8] '(9 8)))
  (is (= [1 2 3 4] (cat [[1 2] [3 4]])))
  (is (= ["a" "very" "nested" "list"] (cat (cat [[["a" "very"] ["nested" "list"]]])))))


(deftest id-test
  (is (= true (id true)))
  (is (= 4 (id 4)))
  (is (= [1 2] (id [1 2]))))


(deftest k-test
  (is (function? (k 1)))
  (is (= 4 (k 4 2)))
  (is (= ((k 2) 3) (k 2 3)))
  (is (true? (tt nil)))
  (is (true? (tt false))))


(deftest distl-test
  (is (= [[9 1] [9 2 2] [9 3]] (distl 9 [[1] [2 2] [3]])))
  (is (= [[9 0 0] [9 1 1] [9 2 2]] (distl 9 [[0 0] [1 1] [2 2]]))))


(deftest distr-test
  (is (= [[1 9] [2 2 9] [3 9]] (distr 9 [[1] [2 2] [3]])))
  (is (= [[0 0 9] [1 1 9] [2 2 9]] (distr 9 [[0 0] [1 1] [2 2]]))))


(deftest insl-test
  (is (= -4 (insl #(- %1 %2) [1 2 3])))
  (is (= 0 (insl #(- %1 %2) [1 2 -1]))))


(deftest insr-test
  (is (= 2 (insr #(- %1 %2) [1 2 3])))
  (is (= -2 (insr #(- %1 %2) [1 2 -1]))))


(deftest aa-test
  (is (function? (aa #(* %1 %1))))
  (is (= [1 4 9] ((aa #(* %1 %1)) [1 2 3])))
  (is (= [1 4 9] (aa #(* %1 %1) [1 2 3]))))


(deftest jvector-test
  (is (= 3.0 (.getX ^Vector3f (jvector 1 3.0))))
  (is (= 3.0 (.getX ^Vector3f (jvector [1] [3]))))
  (let [v1 (jvector [1 3] [4 2])]
    (is (= 4.0 (.getX ^Vector3f v1)))
    (is (= 0.0 (.getY ^Vector3f v1)))
    (is (= 2.0 (.getZ ^Vector3f v1))))
  (let [v1 (jvector [1 2 3] [4 8 2])]
    (is (= 4.0 (.getX ^Vector3f v1)))
    (is (= 8.0 (.getY ^Vector3f v1)))
    (is (= 2.0 (.getZ ^Vector3f v1)))))


(deftest n-test
  (is (= '(10 10 10) (n 3 10))))


(deftest nn-test
  (is (= '(10 10 10) (nn 3 [10]))))


(deftest vectsum-test
  (is (= [8 20 7] (vectsum [0 1 1] [2 2 2] [4 5 2] [2 12 2])))
  (is (= [0 0] (vectsum [0 1] [1 0] [-1 -1])))
  (is (= [11 13 15] (vectsum [10 11 12] [0 1 2] [1 1 1]))))


(deftest vectdiff-test
  (is (= [9 9 9] (vectdiff [10 11 12] [0 1 2] [1 1 1]))))


(deftest meanpoint-test
  (is (= [1.0 1.0 1.0] (meanpoint [0 0 0] [1 1 1] [2 2 2])))
  (is (= [2.0 1.0 1.5] (meanpoint [3 0 0] [1 2 3]))))


(deftest div-test
  (is (= 1.0 (div 10 5 2)))
  (is (= 1.0 (div 10 2 5)))
  (is (= 5.0 (div 20 2 2))))
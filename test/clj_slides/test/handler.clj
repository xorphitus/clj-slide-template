(ns clj-slides.test.handler
  (:use clojure.test
        ring.mock.request
        clj-slides.handler))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "publish"
    (let [html (publish "T_I_T_L_E" "D_E_S_C" "A_U_T_H_O_E_R" [:div "test"])]
      (is (re-find #"<title>T_I_T_L_E</title>" html)))))

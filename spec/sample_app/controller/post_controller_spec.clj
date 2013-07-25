(ns sample-app.controller.post-controller-spec
  (:require [speclj.core :refer [describe around it should= run-specs]]
            [joodo.spec-helpers.controller :refer [do-get rendered-template rendered-context 
                                                   with-mock-rendering with-routes]]
            [sample-app.controller.post-controller :refer [ post-controller blog-post-directory]]))

(describe "post-controller"
          (with-mock-rendering)
          (with-routes post-controller)

          (it "directs to the not_found page if the blog post isn't specified"
              (let [result (do-get "/post")]
                (should= 404 (:status result))
                (should= "not_found" @rendered-template)
                (should= "You must specify a blog post." (:error @rendered-context))))

  (around [it]
          (binding [blog-post-directory (clojure.java.io/file (str
            (. (java.io.File. ".") getCanonicalPath)
            "/spec/sample_app/view/test_posts"))]
            (it)))

  (it "directs to the blog post if the post exists"
      (let [result (do-get "/post/20111215_test-post")]
        (should= 200 (:status result))
        (should= "posts/20111215_test-post" @rendered-template)))

  (it "directs to the not_found page if the post doesn't exist"
      (let [result (do-get "/post/20111215_invalid")]
        (should= 404 (:status result))
        (should= "not_found" @rendered-template)
        (should= "Blog post does not exist." (:error @rendered-context)))))


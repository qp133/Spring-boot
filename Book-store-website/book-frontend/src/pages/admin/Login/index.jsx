
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useLoginMutation } from "../../../app/services/authSevice";
import { ToastContainer, toast } from 'react-toastify';
function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [login] = useLoginMutation();

  // Xử lý login
  const handleLogin = (e) => {
      e.preventDefault();

      const loginRequest = {
          email,
          password,
      };

      login(loginRequest)
          .unwrap()
          .then(() => {
            toast.success("Login thành công");

            setTimeout(() => {
                navigate("/admin/books");
            }, 1500);
          })
          .catch((error) => {
              console.log(error);
              toast.error(error.data.message);
          });
  };
  return (
    <div>
      <div className="container">
        <section className="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
          <div className="container">
            <div className="row justify-content-center">
              <div className="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
                <div className="d-flex justify-content-center py-4">
                  <Link
                    to={"/"}
                    className="logo d-flex align-items-center w-auto"
                  >
                    <img src="assets/img/logo.png" alt="" />
                    <span className="d-none d-lg-block">Stupid</span>
                  </Link>
                </div>

                <div className="card mb-3">
                  <div className="card-body">
                    <div className="pt-4 pb-2">
                      <h5 className="card-title text-center pb-0 fs-4">
                        Login to Your Account
                      </h5>
                      <p className="text-center small">
                        Enter your email & password to login
                      </p>
                    </div>

                    <form 
                     onSubmit={(e) => handleLogin(e)}
                    className="row g-3 needs-validation" noValidate   >
                      <div className="col-12">
                        <label htmlFor="yourUsername" className="form-label">
                          Email
                        </label>
                        <div className="input-group has-validation">
                          
                          <input
                            type="text"
                            name="username"
                            className="form-control"
                            id="yourUsername"
                            value={email}
                            onChange={(e) =>
                                setEmail(e.target.value)}
                            required
                          />
                          <div className="invalid-feedback">
                            Please enter your email.
                          </div>
                        </div>
                      </div>

                      <div className="col-12">
                        <label htmlFor="yourPassword" className="form-label">
                          Password
                        </label>
                        <input
                          type="password"
                          name="password"
                          value={password}
                          onChange={(e) =>
                              setPassword(e.target.value)
                          }
                          className="form-control"
                          id="yourPassword"
                          required
                        />
                        <div className="invalid-feedback">
                          Please enter your password!
                        </div>
                      </div>

                      <div className="col-12">
                      
                      </div>
                      <div className="col-12">
                        <button className="btn btn-primary w-100" type="submit">
                          Login
                        </button>
                      </div>
                      <div className="col-12">
                        <p className="small mb-0">
                          Chưa có tài khoản?{" "}
                          <Link to={"/page-register"}>Create an account</Link>
                        </p>
                      </div>
                    </form>
                  </div>
                </div>

                <div className="credits">
                  Designed by <a href="https://www.facebook.com/profile.php?id=100012461946460">A Quang</a>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      <ToastContainer  position="top-center" />
    </div>
  );
}

export default Login;

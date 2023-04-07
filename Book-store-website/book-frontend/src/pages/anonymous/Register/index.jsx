import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import registerApi from "../../../app/api/registerApi";
import { useForm } from "react-hook-form";

function Register() {
  const {
    register,
    watch,
    formState: { errors },
    handleSubmit,
  } = useForm();
  const password = React.useRef({});
  password.current = watch("password", "");
  const navigate = useNavigate();
 

  // Xử lý login
  const handleRegister = async (data) => {
    // e.preventDefault();
    try {
      const newUser = {
          name:data.userName,
          email:data.email,
          password:data.password,
          repeatPassword:data.repeatPassword 
      };
      await registerApi.createAccount(newUser);
      toast.success("Tạo user thành công");
      console.log(newUser)
      setTimeout(() => {
          navigate("/login");
      }, 1500);
    } catch (e) {
      toast.error(e.response.data.message);
    }
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
                        Create Your Account
                      </h5>
                      <p className="text-center small">
                        Enter your user Name & email & password to register
                      </p>
                    </div>

                    <form
                      onSubmit={handleSubmit(handleRegister)}
                      className="row g-3 needs-validation"
                      noValidate
                    >
                      <div className="col-12">
                        <label htmlFor="yourUsername" className="form-label">
                          User Name
                        </label>
                        <div className="register">
                          <input
                            type="text"
                            name="username"
                            className="form-control"
                            id="yourUsername"
                            {...register("userName", {  required: {
                              value:true,
                              message:"User name không được để trống"
                            } ,maxLength:{
                              value:12,
                              message:"User name có độ dài từ 6-12 kí tự"
                            },
                            minLength:{
                              value:6,
                              message:"User name có độ dài từ 6-12 kí tự"
                            }
                          })}
                            aria-invalid={errors.userName ? "true" : "false"}
                            required
                          />
                          {errors.userName?.message && (
                            <p style={{"marginTop":"5px","color":"red"}} role="alert">{errors.userName.message}</p>
                          )}
                          <div className="invalid-feedback">
                            Please enter your userName.
                          </div>
                        </div>
                      </div>
                      <div className="col-12">
                        <label htmlFor="yourUsername" className="form-label">
                          Email
                        </label>
                        <div className="register">
                          <input
                            type="text"
                            name="username"
                            className="form-control"
                            id="yourEmail"
                            {...register("email", { required: {
                              value:true,
                              message:"Email không được để trống"
                            } ,   pattern: {
                              value: /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                              message: 'Email không đúng định dạng',
                            }})}
                            aria-invalid={errors.email ? "true" : "false"}
                            required
                          />
                            {errors.email?.message && (
                            <p style={{"marginTop":"5px","color":"red"}} role="alert">{errors.email.message} </p>
                          )}
                          <div className="invalid-feedback">
                            Please enter your email.
                          </div>
                        </div>
                      </div>

                      <div className="col-12">
                        <label htmlFor="yourPassword" className="form-label">
                          Password
                        </label>
                        <div className="register">
                        <input
                          type="password"
                          name="password"
                          {...register("password", {  required: {
                            value:true,
                            message:"Password không được để trống"
                          } ,maxLength:{
                            value:12,
                            message:"Password có độ dài từ 6-12 kí tự"
                          },
                          minLength:{
                            value:6,
                            message:"Password có độ dài từ 6-12 kí tự"
                          }
                        })}
                          className="form-control"
                          id="yourPassword"
                          required
                        />
                          {errors.password?.message && (
                            <p style={{"marginTop":"5px","color":"red"}} role="alert">{errors.password.message} </p>
                          )}
                        <div className="invalid-feedback">
                          Please enter your password!
                        </div>
                        </div>
                      
                      </div>
                      <div className="col-12">
                        <label htmlFor="yourPassword" className="form-label">
                          Repeat password
                        </label>
                        <div className="register">
                        <input
                          type="password"
                          name="password"
                          {...register("repeatPassword", {required: {
                            value:true,
                            message:"Password không được để trống"
                          },
                          validate:value=> value === password.current || "Mật khẩu nhập lại không khớp"
                        })}
                          aria-invalid={errors.repeatPassword ? "true" : "false"}
                          className="form-control"
                          id="yourRepeatPassword"
                          required
                        />
                         {errors.repeatPassword?.message  && (
                            <p style={{"marginTop":"5px","color":"red"}} role="alert">{errors.repeatPassword.message}</p>
                          )}
                        <div className="invalid-feedback">
                          Please enter repeat password!
                        </div>
                        </div>
                       
                      </div>

                      <div className="col-12"></div>
                      <div className="col-12">
                        <button className="btn btn-primary w-100" type="submit">
                          Register
                        </button>
                      </div>
                      <div className="col-12">
                        <p className="small mb-0">
                          Đã có tài khoản?{" "}
                          <Link to={`/login`}>Login</Link>
                        </p>
                      </div>
                    </form>
                  </div>
                </div>

                <div className="credits">
                  Designed by{" "}
                  <a href="https://www.facebook.com/profile.php?id=100012461946460">
                    A Quang
                  </a>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      <ToastContainer position="top-center" />
    </div>
  );
}

export default Register;

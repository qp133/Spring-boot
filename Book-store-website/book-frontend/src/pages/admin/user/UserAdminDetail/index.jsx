import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';

import { useSelector } from "react-redux";
import {
  useUpdateUserMutation,
  useUploadAvatarMutation,
} from "../../../../app/services/userSevice";
function UserAdminDetail() {
  const navigate=useNavigate();
  // const location=useLocation();
  // navigate(location.state?.from)
  const { userId } = useParams();
  const { users } = useSelector((state)=>state.users);
  // const [oldPassword, setOldPassword] = useState("");
  // const [newPassWord, setNewPassword] = useState("");
  const [user, setUser] = useState(() =>{
    console.log(userId)
    console.log(users)
    return users.find((u)=>u.id===+userId)
  });
  console.log(user)
  // const [defaultPassword, setDefaultPassword] = useState("");
  const [file, setFile] = useState(null);
  const [editUser] = useUpdateUserMutation();
  const [uploadAvatar] = useUploadAvatarMutation();
  const handleOnChange = (e) => {
    setFile(e.target.files[0]);
    
  };
  const handleSubmit = (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append("file", file);
    console.log(file)
    uploadAvatar({ id: userId, file: formData })
      .unwrap()
      .then((res) => {
        toast.success("Cập nhật avatar thành công");
        setTimeout(() => {
            navigate(`/admin/users`);
        }, 1500);
        
      })
      .catch((err) =>console.log(err));
  };

  const updateUser = () => {
    if (user.name === "") {
      alert("Tên không được để trống");
      return;
    }

    editUser({ id: userId, ...user, name: user.name })
      .unwrap()
      .then(() => {
        toast.success("Cập nhật user thành công");

        setTimeout(() => {
            navigate("/admin/users");
        }, 1500);

      
      })
      .catch((err) => alert(err));
     
  };
  // const forgotPassword = () => {};
  // const changePassword = () => {};
  return (
    <div className="container mt-5 mb-5">
      <h2 className="text-center text-uppercase mb-3">Thông tin user</h2>

      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="bg-light p-4">
            <div className="mb-3 avatar-user-edit">
              <label className="form-label">Avatar</label>
              <div className="avatar-preview mb-3 rounded">
                <img
                  src={
                    user?.avatar
                      ? `${user?.avatar}`
                      : "https://via.placeholder.com/150"
                  }
                  alt="avatar"
                  id="avatar-preview"
                  className="rounded-circle"
                  style={{ width: "150px" }}
                />
              </div>
              <form onSubmit={handleSubmit}>
                <label htmlFor="avatar" className="btn btn-info">
                  Thay đổi
                </label>
                <input
                  onChange={handleOnChange}
                  type="file"
                  id="avatar"
                  className="d-none"
                />
                <button className="btn btn-success" type="submit">
                  Xác nhận
                </button>
              </form>
            </div>
            <div className="mb-3">
              <label className="col-form-label">Name</label>
              <input
                type="text"
                id="name"
                className="form-control"
                value={user?.name || ""}
                onChange={(e) => setUser({ ...user, name: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label className="col-form-label">Email</label>
              <input
                type="text"
                id="email"
                className="form-control"
                disabled
                defaultValue={user?.email || ""}
              />
            </div>

            <div className="mb-3">
              <label className="col-form-label">Password</label>
              <div className="">
                {/* <button
                  type="button"
                  className="btn btn-primary"
                  data-bs-toggle="modal"
                  data-bs-target="#modal-change-password"
                >
                  Đổi mật khẩu
                </button>
                <button
                  className="btn btn-warning"
                  id="btn-forgot-password"
                  data-bs-toggle="modal"
                  data-bs-target="#modal-forgot-password"
                  onClick={forgotPassword}
                >
                  Quên mật khẩu
                </button> */}
              </div>
            </div>
          </div>
          <div className="text-center mt-3">
            <button className="btn btn-secondary btn-back">
              <Link className="navbar-brand" to={"/users"}>
                Quay lại
              </Link>
            </button>
            <button
              onClick={updateUser}
              className="btn btn-success"
              id="btn-save"
            >
              Cập nhật
            </button>
          </div>
        </div>
      </div>

      {/* <div
        className="modal fade"
        id="modal-change-password"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabIndex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="staticBackdropLabel">
                Đổi mật khẩu
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <div className="mb-3">
                <label className="col-form-label">Mật khẩu cũ</label>
                <input
                  type="text"
                  id="old-password"
                  value={oldPassword}
                  onChange={(e) => setOldPassword(e.target.value)}
                  className="form-control"
                />
              </div>
              <div className="mb-3">
                <label className="col-form-label">Mật khẩu mới</label>
                <input
                  type="text"
                  id="new-password"
                  value={newPassWord}
                  onChange={(e) => setNewPassword(e.target.value)}
                  className="form-control"
                />
              </div>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Đóng
              </button>
              <button
                type="button"
                className="btn btn-primary"
                id="btn-change-password"
                onClick={changePassword}
              >
                Xác nhận
              </button>
            </div>
          </div>
        </div>
      </div> */}

      {/* <div
        className="modal fade"
        id="modal-forgot-password"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabIndex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="staticBackdropLabel">
                Quên mật khẩu
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <div className="mb-3">
                <h5>Mật khẩu mới của bạn là:{defaultPassword}</h5>
              </div>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Đóng
              </button>
              <button
                type="button"
                className="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#modal-change-password"
              >
                Đổi mật khẩu
              </button>
            </div>
          </div>
        </div>
      </div> */}
      <ToastContainer  position="top-center" />
    </div>
  );
}

export default UserAdminDetail;

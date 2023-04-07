import React, { useState } from "react";
import Select from "react-select";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import { useCreateUserMutation } from "../../../../app/services/userSevice";
import { Link } from "react-router-dom";
function UserAdminCreate() {
  const navigate=useNavigate();
  const [userName, setUserName] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [userPassword, setUserPassword] = useState("");
  const [roles,setRoles]=useState([]);
  const [createUser] = useCreateUserMutation();

  let options = [
    { value: "ADMIN", label: "ADMIN" },
    { value: "USER", label: "USER" },
  ];

  const handleChangeRoles = (e) => {
    let arr = [];
    for (let i = 0; i < e.length; i++) {
        arr.push(e[i].value)
    }
    
    setRoles(arr);
  };
  const handleAddUser = async () => {
    if (!userName || !userEmail || !userPassword) {
      alert("Bạn cần điền hết mọi thông tin");
      return;
    }
    let newUser = {
      name: userName,
      email: userEmail,
      password: userPassword,
      roles:roles,
    };
    console.log(newUser);
    createUser(newUser)
    .unwrap()
    .then(() => {
      toast.success("Tạo user thành công");

      setTimeout(() => {
          navigate("/admin/users");
      }, 1500);
    })
    .catch((err) => alert(err.data.message));

  };
  return (
    <>
      <div className="container mt-5 mb-5">
        <h2 className="text-center text-uppercase mb-3">Tạo user</h2>

        <div className="row justify-content-center">
          <div className="col-md-6">
            <div className="bg-light p-4">
              <div className="mb-3">
                <label className="col-form-label">Name</label>
                <input
                  type="text"
                  id="name"
                  onChange={(e) => setUserName(e.target.value)}
                  value={userName}
                  className="form-control"
                />
              </div>
              <div className="mb-3">
                <label className="col-form-label">Email</label>
                <input
                  type="text"
                  id="email"
                  value={userEmail}
                  onChange={(e) => setUserEmail(e.target.value)}
                  className="form-control"
                />
              </div>
              <div className="mb-3">
                <label className="col-form-label">Password</label>
                <input
                  type="text"
                  id="password"
                  value={userPassword}
                  onChange={(e) => setUserPassword(e.target.value)}
                  className="form-control"
                />
              </div>
              <div className="mb-3">
                <label htmlFor="course-topic" className="form-label fw-bold">
                  Roles
                </label>
                <Select
                  closeMenuOnSelect={false}
                  onChange={handleChangeRoles}
                  isMulti
                  options={options}
                />
              </div>
            </div>

            <div className="text-center mt-3">
              <button className="btn btn-secondary btn-back">
                <Link className="navbar-brand" to={"/users"}>
                  Quay lại
                </Link>
              </button>
              <button
                className="btn btn-success"
                id="btn-save"
                onClick={handleAddUser}
              >
                Tạo User
              </button>
            </div>
          </div>
        </div>
      </div>
      <ToastContainer position="top-center" />
    </>
  );
}

export default UserAdminCreate;
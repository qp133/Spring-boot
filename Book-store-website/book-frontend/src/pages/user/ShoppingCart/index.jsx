import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import {
  useDeleteOrderItemMutation,
  useGetOrderItemsQuery,
  useUpdateOrderItemMutation,
} from "../../../app/services/orderItemService";
import axios from "axios";
import { useCreateOrderMutation } from "../../../app/services/orderUserService";

function ShoppingCart() {
  const { auth } = useSelector((state) => state.auth);
  const { isLoading } = useGetOrderItemsQuery(auth.id);
  const { orderItems } = useSelector((state) => state.orderItems);
  const [editOrderItem] = useUpdateOrderItemMutation();
  const [deleteOrderItem] = useDeleteOrderItemMutation();
  const [createOrder] = useCreateOrderMutation();
  const [address, setAddress] = useState("");
  const [provinces, setProvinces] = useState([]);
  const [districts, setDistricts] = useState([]);
  const [wards, setWards] = useState([]);


  useEffect(() => {
    const fetchProvinces = async () => {
      try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        setProvinces(res.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchProvinces();
  }, []);

  const fetchDistricts = async (provinceCode) => {
    try {
      let res = await axios.get(
        `https://provinces.open-api.vn/api/p/${provinceCode}?depth=2`
      );
      setAddress(res.data.name)
      setDistricts(res.data.districts);
      setWards([]);
    } catch (error) {
      console.log(error);
    }
  };

  const fetchWards = async (districtCode) => {
    try {
      let res = await axios.get(
        `https://provinces.open-api.vn/api/d/${districtCode}?depth=2`
      );
      setAddress(address+"-"+res.data.name)
      setWards(res.data.wards);
    } catch (error) {
      console.log(error);
    }
  };

  if (isLoading) {
    return <h3>Loading ...</h3>;
  }

  let sumPrice = 0;
  for (let i = 0; i < orderItems.length; i++) {
    sumPrice += orderItems[i].book.price * orderItems[i].amount;
  }

  let vat = sumPrice * 0.1;
  let sum = sumPrice + vat;

  const tru = (id) => {
    const orderItem = orderItems.find((c) => c.id === id);
    if (orderItem.amount <= 1) {
      alert("Số lượng không thể nhỏ hơn 1");
    } else {
      let newAmount = orderItem.amount - 1;

      editOrderItem({ id, ...orderItem, amount: newAmount })
        .unwrap()
        .then()
        .catch((err) => alert(err));
    }
  };

  const cong = (id) => {
    const orderItem = orderItems.find((c) => c.id === id);

    let newAmount = orderItem.amount + 1;

    editOrderItem({ id, ...orderItem, amount: newAmount })
      .unwrap()
      .then()
      .catch((err) => alert(err));
  };
  const handleDelete = (id) => {
    const isDelete = window.confirm("Bạn có muốn xóa không?");
    if (isDelete) {
      deleteOrderItem(id);
      console.log(orderItems);
    }
  };
const orderItemIds=orderItems.map((o)=>{
  return o.id;
})


const updateAllOrderItems = (id) => {
    const orderItem = orderItems.find((c) => c.id === id);
    editOrderItem({id,...orderItem,status:false});
    }

  const handleAddOrder = () => {

      const newOrder = {
        user: auth,
        orderItemIds: orderItemIds,
        address: address,
      };
 
        console.log(newOrder)
        createOrder(newOrder)
        .unwrap()
        .then(() =>{ alert("Đặt hàng thành công");
         updateAllOrderItems(1);
      })
        .catch((err) => alert(err.data.message));
      
  }

  return (
    <div className="shopping-cart-container mt-5">
      <div className="container">
        <div className="row">
          <div className="col-md-12">
            <div className="mb-4">
              <h2>Giỏ hàng</h2>
            </div>
          </div>
        </div>

        <div className="row shopping-cart">
          <div className="col-md-8">
            {orderItems.length === 0 && (
              <p className="fst-italic message">
                {" "}
                Không có sản phẩm nào trong giỏ hàng
              </p>
            )}
            <div className="product-list">
              {orderItems.length > 0 &&
                orderItems.map((orderItem) => (
                  <div
                    key={orderItem.id}
                    className="product-item d-flex border mb-4"
                  >
                    <div className="image ">
                      <img src={orderItem.book.thumbnail} alt="sản phẩm 1" />
                    </div>
                    <div className="info d-flex flex-column justify-content-between px-4 py-3 flex-grow-1">
                      <div>
                        <div className="d-flex justify-content-between align-items-center">
                          <h2 className="text-dark fs-5 fw-normal">
                            {orderItem.book.title}
                          </h2>
                          <h2 className="text-danger fs-5 fw-normal">
                            {orderItem.book.price.toLocaleString("en")}
                          </h2>
                        </div>
                        <div className="text-black-50">
                          <div className="d-inline-block me-3">
                            <button
                              onClick={() => tru(orderItem.id)}
                              className="border py-2 px-3 d-inline-block fw-bold bg-light"
                            >
                              -
                            </button>
                            <span className="py-2 px-3 d-inline-block fw-bold">
                              {orderItem.amount}
                            </span>
                            <button
                              onClick={() => cong(orderItem.id)}
                              className="border py-2 px-3 d-inline-block fw-bold bg-light"
                            >
                              +
                            </button>
                          </div>
                        </div>
                      </div>
                      <div>
                        <button
                          onClick={() => handleDelete(orderItem.id)}
                          className="text-primary border-0 bg-transparent fw-light"
                        >
                          <span>
                            <i className="fa-solid fa-trash-can"></i>
                          </span>
                          Xóa
                        </button>
                      </div>
                    </div>
                  </div>
                ))}
            </div>
          </div>
          <div className="col-md-4">
            <div className="bill">
              <div className="border mb-2 p-3 fs-5 fw-normal d-flex justify-content-between align-items-center">
                <span className="text-black-50">Tạm tính:</span>
                <span className="text-primary" id="sub-total-money">
                  {sumPrice.toLocaleString("en")} VND
                </span>
              </div>
              <div className="border mb-2 p-3 fs-5 fw-normal d-flex justify-content-between align-items-center">
                <span className="text-black-50">VAT (10%):</span>
                <span className="text-primary" id="vat-money">
                  {vat.toLocaleString("en")} VND
                </span>
              </div>
              <div className="border mb-2 p-3 fs-5 fw-normal d-flex justify-content-between align-items-center">
                <span className="text-black-50">Thành tiền:</span>
                <span className="text-primary" id="total-money">
                  {sum.toLocaleString("en")} VND
                </span>
              </div>
              <label className="col-form-label">Address</label>

              <select
                id="province"
                className="form-select mb-3" 
                onChange={(e) => fetchDistricts(e.target.value)}
              >
                <option hidden>-- Chọn tỉnh/thành phố</option>
                {provinces.map((p) => (
                  <option value={p.code} key={p.code}>
                    {p.name}
                  </option>
                ))}
              </select>

              <select
                className="form-select mb-3"
                id="district"
                onChange={(e) => fetchWards(e.target.value)}
              >
                <option hidden>-- Chọn quận/huyện</option>
                {districts.map((p) => (
                  <option value={p.code} key={p.code}>
                    {p.name}
                  </option>
                ))}
              </select>

              <select 
              className="form-select mb-3" id="commune"
              onChange={(e)=>setAddress(address+"-"+e.target.value)}>
                <option hidden>-- Chọn xã/phường</option>
                {wards.map((p) => (
                  <option value={p.name} key={p.code}>
                    {p.name}
                  </option>
                ))}
              </select>
              <button  onClick={handleAddOrder} className="tm-btn tm-btn-blue ">
                Đặt hàng
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ShoppingCart;

import React from 'react'
import { useSelector } from 'react-redux';
import { useGetOrdersQuery } from '../../../../app/services/orderService'
import { Link } from 'react-router-dom';
import { convertDate } from '../../../../utils/utils';

function OrderList() {
    const {isLoading}=useGetOrdersQuery();
    const {orders}=useSelector((state)=>state.orders);
    if (isLoading) {
        return <h3>Loading ...</h3>;
      }
      console.log(orders)
      
  return (
    <div className="course-list mt-4 mb-4">
      <div className="container">
        <div className="mb-4">
      
        </div>

        <div className="course-list-inner p-2">
          <table className="table course-table book-table">
            <thead>
              <tr>
                <th>STT</th>       
                <th>Avatar</th>
                <th>Người đặt hàng</th>
                <th>Trạng thái</th>
                <th>Ngày đặt</th>
                <th>Địa chỉ</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {orders.map((order, index) => (
                <tr key={order.id}>
                  <td> <Link to={`/admin/orders/${order.id}`}>{index+1}</Link></td>
                  <td>
                  <Link to={`/admin/orders/${order.id}`}>
                    <img
                      src={order?.orderItems[0]?.user?.avatar?`${order?.orderItems[0]?.user?.avatar}`: "https://via.placeholder.com/150"}
                      className="rounded-circle"
                      style={{ width: "40px" }}
                      alt={order?.orderItems[0]?.user?.name}
                    />
                    </Link>
                  </td>
                  <td>
                    <Link to={`/admin/orders/${order.id}`}>{order?.orderItems[0]?.user.name}</Link>
                  </td>     
                    
                  <td>
                  {order.status===false&&(
                    <p style={{"color":"red"}}>Chưa xác nhận</p>
                  )}
                  {order.status===true&&(
                    <p style={{color:"#00CC00"

                  }}>Đã xác nhận</p>
                  )}
                  </td>
                  <td>{convertDate(order.createAt)}</td>
                  <td>{order.address}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default OrderList
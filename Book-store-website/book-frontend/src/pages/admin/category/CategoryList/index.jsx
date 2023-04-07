import React from 'react'
import { useSelector } from 'react-redux'
import { useCreateCategoryMutation, useDeleteCategoryMutation, useGetCategoriesQuery, useUpdateCategoryMutation } from '../../../../app/services/categoryService';

function CategoryList() {
  const {categories} = useSelector((state) => state.categories);
  const [createCategory]=useCreateCategoryMutation();
  const [editCategory]=useUpdateCategoryMutation();
  const [deleteCategory]=useDeleteCategoryMutation();
  const { isLoading } = useGetCategoriesQuery();
  if ( isLoading ) {
      return <h2>Loading...</h2>
  }

  const handleDelete = (id) => {
      const isDelete = window.confirm("Bạn có muốn xóa không?")
      if(isDelete) {
          deleteCategory(id);
      }
  }
  const handeleEdit = (id) => {
    const category = categories.find(c => c.id === id);
    const editName = window.prompt("Enter update name", category.name);
    if (editName === null) {
      return;
    }
    if ( editName === "") {
      alert("Tên không được để trống")
      return;
    }
    editCategory({id, name : editName})
      .unwrap()
      .then(() => alert("Sửa thành công"))
      .catch(err => alert(err))
  }
  const handleAddCategory=()=>{
    const name =window.prompt("Enter Name");
    if(name===null){
      return
    }
    if(name===""){
      alert("tiêu đề không được để trống");
      return
    }
    createCategory({name})
    .unwrap()
    .then(() => alert("Thêm thành công"))
    .catch((err) => console.log(err));
  }

  return (
    <div className="course-list mt-4 mb-4">
    <div className="container">
        <div className="mb-4">
            <button 
            onClick={handleAddCategory}
            className="btn btn-success">
               <i className="fa-solid fa-plus"></i> Thêm category
            </button>
        </div>

        <div className="course-list-inner p-2">
            <table className="table course-table">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên category</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {categories.map((category, index) => (
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{category.name}</td>
                            <td>
                                <button 
                                onClick={()=>handeleEdit(category.id)}
                                className='btn btn-info'>Edit</button>
                                <button className='btn btn-danger'
                                        onClick={() => handleDelete(category.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    </div>
</div>
  )
}

export default CategoryList
import React from 'react'
import { useSelector } from 'react-redux'
import { useGetAuthorsQuery,useCreateAuthorMutation, useUpdateAuthorMutation,useDeleteAuthorMutation} from '../../../../app/services/authorSevice';

function AuthorList() {
  const {authors} = useSelector((state) => state.authors);
  const [createAuthor]=useCreateAuthorMutation();
  const [editAuthor]=useUpdateAuthorMutation();
  const [deleteAuthor]=useDeleteAuthorMutation();
  const { isLoading } = useGetAuthorsQuery();
  if ( isLoading ) {
      return <h2>Loading...</h2>
  }

  const handleDelete = (id) => {
      const isDelete = window.confirm("Bạn có muốn xóa không?")
      if(isDelete) {
          deleteAuthor(id);
      }
  }
  const handeleEdit = (id) => {
    const author = authors.find(c => c.id === id);
    const editName = window.prompt("Enter update name", author.name);
    if (editName === null) {
      return;
    }
    if ( editName === "") {
      alert("Tên không được để trống")
      return;
    }
    editAuthor({id, name : editName})
      .unwrap()
      .then(() => alert("Sửa thành công"))
      .catch(err => alert(err))
  }
  const handleAddAuthor=()=>{
    const name =window.prompt("Enter Name");
    if(name===null){
      return
    }
    if(name===""){
      alert("tiêu đề không được để trống");
      return
    }
    createAuthor({name})
    .unwrap()
    .then(() => alert("Thêm thành công"))
    .catch((err) => console.log(err));
  }

  return (
    <div className="course-list mt-4 mb-4">
    <div className="container">
        <div className="mb-4">
            <button 
            onClick={handleAddAuthor}
            className="btn btn-success">
               <i className="fa-solid fa-plus"></i> Thêm author
            </button>
        </div>

        <div className="course-list-inner p-2">
            <table className="table course-table">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên author</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {authors.map((author, index) => (
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{author.name}</td>
                            <td>
                                <button 
                                onClick={()=>handeleEdit(author.id)}
                                className='btn btn-info'>Edit</button>
                                <button className='btn btn-danger'
                                        onClick={() => handleDelete(author.id)}
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

export default AuthorList
import React from "react";

export function Menu(props) {
    const id = props.menuId
    const menuName = props.menuName;
    const category = props.category;
    const price = props.price;
    const imagePath = props.imagePath;
    const handleAddBtnClicked = e => {
        props.onAddClick(id);
    }
    return (
        <React.Fragment>
            <div class="col-2"><img class="img-fluid" src={imagePath} alt="" /></div>
            <div class="col">
                <div class="row text-muted">{category}</div>
                <div class="row">{menuName}</div>
            </div>
            <div class="col text-center price">{price}</div>
            <div class="col text-end action">
                <button onClick={handleAddBtnClicked} class="btn btn-small btn-outline-dark" href="">추가</button>
            </div>
        </React.Fragment>
    )
}
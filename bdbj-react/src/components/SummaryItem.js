import React from "react"

export function SummaryItem({ id, menuName, count, onRemoveClick }) {
    const handleRemoveBtnClicked = e => {
        onRemoveClick(id);
        console.log(id);
    }
    return (
        <div class="row">
            <h6 class="p-0">
                {menuName}
                <span class="badge bg-dark text-">{count}개</span>
                <button class="ms-1 badge btn-danger" onClick={handleRemoveBtnClicked}>삭제</button>
            </h6>
        </div>
    )
}
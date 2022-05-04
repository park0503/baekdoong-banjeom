import React, { useState } from "react";
import { SummaryItem } from "./SummaryItem";

export function Summary({ items = [], onOrderSubmit, onRemoveClick }) {
    const totalPrice = items.reduce((prev, curr) => prev + (curr.price * curr.count), 0);
    const [order, setOrder] = useState({
        phoneNumber: "", address: "", detailedAddress: "", postcode: ""
    });
    const handlePhoneNumberInput = (e) => {
        setOrder({
            ...order, phoneNumber: e.target.value
        });
    }
    const handleAddressInput = (e) => {
        setOrder({
            ...order, address: e.target.value
        });
    }
    const handleDetailedAddressInput = (e) => {
        setOrder({
            ...order, detailedAddress: e.target.value
        });
    }
    const handlePostcodeInput = (e) => {
        setOrder({
            ...order, postcode: e.target.value
        });
    }
    const handleSubmit = (e) => {
        if (order.address === "" || order.detailedAddress === "" || order.phoneNumber === "" || order.postcode === "") {
            alert("입력값을 확인해주세요!")
        } else {
            onOrderSubmit(order);
        }
        console.log(order);
    }
    return (
        <React.Fragment>
            <div>
                <h5 class="m-0 p-0"><b>Summary</b></h5>
            </div>
            <hr />
            {items.map(v => <SummaryItem key={v.menuId} id={v.menuId} count={v.count} menuName={v.menuName} onRemoveClick={onRemoveClick} />)}
            <form>
                <div class="mb-3">
                    <label for="phoneNumber" class="form-label">전화번호</label>
                    <input type="phoneNumber" class="form-control mb-1" id="phoneNumber" value={order.phoneNumber} onChange={handlePhoneNumberInput} />
                </div>
                <div class="mb-3">
                    <label for="postcode" class="form-label">우편번호</label>
                    <input type="text" class="form-control" id="postcode" value={order.postcode} onChange={handlePostcodeInput} />
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">주소</label>
                    <input type="text" class="form-control mb-1" id="address" value={order.address} onChange={handleAddressInput} />
                </div>
                <div class="mb-3">
                    <label for="detailedAddress" class="form-label">상세주소</label>
                    <input type="text" class="form-control mb-1" id="detailedAddress" value={order.detailedAddress} onChange={handleDetailedAddressInput} />
                </div>
                <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
            </form>
            <div class="row pt-2 pb-2 border-top">
                <h5 class="col">총금액</h5>
                <h5 class="col text-end">{totalPrice}원</h5>
            </div>
            <button class="btn btn-dark col-12" onClick={handleSubmit}>결제하기</button>
        </React.Fragment>
    )
}
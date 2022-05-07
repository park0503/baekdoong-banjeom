import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import { MenuList } from "./components/MenuList"
import { Summary } from './components/Summary';
import { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
    const [allMenus, setAllMenus] = useState([
    ]);
    const [menus, setMenus] = useState([
    ]);
    const [categories, setCategories] = useState([
    ]);
    const [items, setItems] = useState([
    ]);
    const [searchField, setSearchField] = useState([
    ]);
    const handleAddClicked = id => {
        const menu = allMenus.find(v => v.menuId === id);
        const found = items.find(v => v.menuId === id);
        const updatedItems = found ? items.map(v => v.menuId === id ? { ...v, count: v.count + 1 } : v) : [...items, { ...menu, count: 1 }];
        setItems(updatedItems);
        console.log(menus.find(v => v.menuId === id), "added!");
    };
    const handleRemoveClicked = id => {
        setItems(items.filter(i => i.menuId !== id))
    };
    const handleOrderSubmit = (order) => {
        if (items.length === 0) {
            alert("상품을 추가해 주세요.");
        } else {
            axios.post("http://localhost:8080/api/v1/orders/new", {
                phoneNumber: order.phoneNumber,
                address: order.address,
                detailedAddress: order.detailedAddress,
                postcode: order.postcode,
                orderItems: items.map(i => ({
                    menuId: i.menuId,
                    menuName: i.menuName,
                    category: i.category,
                    price: i.price,
                    quantity: i.count
                }))
            }).then(v => alert("주문이 정상적으로 접수되었습니다."), e => {
                alert("서버 장애");
                console.error(e);
            });
        }
        console.log(order, items);
    }
    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/menus')
            .then(v => {
                setAllMenus(v.data);
                setMenus(v.data);
            });
    }, []);
    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/categories')
            .then(v => setCategories(v.data));
    }, []);
    useEffect(() => {
        setMenus(allMenus.filter(m => m.menuName.replace(/ /g, "").includes(searchField.replace(/ /g, ""))))
    }, [searchField])
    return (
        <div className="App">
            <div class="container-fluid">
                <div class="row justify-content-center m-4">
                    <h1 class="text-center">백둥반점</h1>
                </div>
                <div class="card">
                    <div class="row">
                        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                            <h5 class="flex-grow-0"><b>메뉴판</b></h5>
                            <input type="search" placeholder="메뉴를 검색해 보세요." onChange={e => setSearchField(e.target.value)} />
                            <MenuList menus={menus} categories={categories} onAddClick={handleAddClicked} />
                        </div>
                        <div class="col-md-4 summary p-4">
                            <Summary items={items} onRemoveClick={handleRemoveClicked} onOrderSubmit={handleOrderSubmit} />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default App;

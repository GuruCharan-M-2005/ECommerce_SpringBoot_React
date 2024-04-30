import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import Navbar from "./Navbar";
import Footer from "../footer/Footer";
import axios from "axios";
import './Filter.css'
import { BsCurrencyRupee } from "react-icons/bs";
import { CartContext } from "../cart/CartContext";
import { IoTrashBinOutline } from "react-icons/io5";
import LoadingScreenAdded from "../loadingscreen/LoadingScreenLogout";


const FilteredItemsPage = () => {
  const [filteredItems, setFilteredItems] = useState([]);
  const location = useLocation();
  const searchQuery = new URLSearchParams(location.search).get("searchQuery");
  const [items, setItems] = useState([]);
  const [cart,setCart]=useState([])
  // const [search,setSeach]=useState('')
  // const { cart, addToCart, removeFromCart } = useContext(CartContext); 
  const [loading, setLoading] = useState(false);
  const [showSuccessImage, setShowSuccessImage] = useState(false);

  async function fetchFilteredItems() {
    try {
      const response = await axios.get(`http://localhost:8080/api/filtered-items?searchQuery=${searchQuery}`);
      setFilteredItems(response.data);
    } catch (error) {
      console.error("Error fetching filtered items:", error);
    }
  }

 

  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/type/all");
      setItems(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    setTimeout(() => {
      fetchData();
      addToCart();
      fetchFilteredItems();
      // setSeach(searchQuery)
      
    }, 100);
  }, []);

  const addToCart = async() => {
    const res=await axios.get("http://localhost:8080/api/cart");
    setCart(res.data);
    
  };

  const handleAddToCart = async (item) => {
    const res=await axios.put(`http://localhost:8080/api/cart/${item.id}/inc`,item);
    const existingItem = cart.find((cartItem) => cartItem.id === item.id);
      setLoading(false);
      setShowSuccessImage(true); 
 
      setTimeout(() => {
        addToCart();
        fetchData();
        setShowSuccessImage(false); 
      }, 3000);
    // }
  };

  const handleRemoveFromCart=async (item)=>{
    const res=await axios.put(`http://localhost:8080/api/cart/${item.id}/remove`,item);
    setTimeout(() => {
      fetchData();
    }, 100);
  }

  return (
    <div>
      <Navbar />
      
      <div className="product-container">
        {filteredItems.map((item) => (
          <div key={item.id} className="product-box">
            <div className="product-image">
              <img src={item.image} alt="Product" />
            </div>
            <div className="product-details">
              <h3 className="product-price">
                <BsCurrencyRupee />
                {item.price}
              </h3>
              <p className="product-description">{item.description}</p>
            </div>
            <div className="cart-button">
              {(filteredItems.count>0) ? (
                <button onClick={() => handleRemoveFromCart(item)} className="bin">
                 <IoTrashBinOutline />
                </button>
              ) : (
                <button onClick={() => handleAddToCart(item)} className="add">
                  Add to Cart
                </button>
              )}
            </div>
          </div>
        ))}
      </div>
      <Footer />
      {loading && <LoadingScreenAdded />} 
      {showSuccessImage && <LoadingScreenAdded />} 
    </div>
  );
};

export default FilteredItemsPage;

import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import Navbar from "../navbar/Navbar";
import Footer from "../footer/Footer";
import "./AllProduct.css";
import { BsCurrencyRupee } from "react-icons/bs";
import { CartContext } from "../cart/CartContext";
import { IoTrashBinOutline } from "react-icons/io5";
import LoadingScreenAdded from "../loadingscreen/LoadingScreenLogout";

export default function Watches() {
  const [items, setItems] = useState([]);
  const [cart,setCart]=useState([])
  // const { cart, addToCart, removeFromCart } = useContext(CartContext); 
  const [loading, setLoading] = useState(false);
  const [showSuccessImage, setShowSuccessImage] = useState(false);
  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/type/watch");
      setItems(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  useEffect(() => {
    setTimeout(() => {
      fetchData();
      addToCart();
      
    }, 100);
  }, []);

  const handleAddToCart = async (item) => {
    const res=await axios.put(`http://localhost:8080/api/cart/${item.id}/inc`,item);

    const existingItem = cart.find((cartItem) => cartItem.id === item.id);
    // if (existingItem) {
    //   handleRemoveFromCart(item.id);
    //   fetchData();
    //   addToCart();
    // } else {
      // addToCart(item);
      setLoading(false);
      setShowSuccessImage(true); 
 
      setTimeout(() => {
        addToCart();
        fetchData();
        setShowSuccessImage(false); 
      }, 3000);
    // }
  };


  const addToCart = async() => {
    const res=await axios.get("http://localhost:8080/api/cart");
    setCart(res.data);
    
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
        {items.map((item) => (
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
              {(item.count>0) ? (
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
}

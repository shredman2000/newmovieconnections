import { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import './HomePage.css'
function HomePage() {
    const navigate = useNavigate();
    const clues = [
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"
    ]
    return (
        <>
        <div className='main-page-wrapper'>
            <div className='content-wrapper'>
                <h1 className='main-page-title'>Movie Connections</h1>

                <div className='board-container'>
                {clues.map((clue, index) => (
                    <div key={index} className='clue-tile'>
                    {clue}
                    </div>
                ))}
                </div>
            </div>
            <button className='admin-button' onClick={() => navigate('/admin')}>Admin</button>
        </div>
        </>
    )
}

export default HomePage;
import { useState } from 'react'
import { Routes, Route, Navigate } from 'react-router-dom';
import AdminPage from './AdminPage.jsx'
import HomePage from './HomePage';

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/admin" element={<AdminPage />} />
    </Routes>
  )
}

export default App

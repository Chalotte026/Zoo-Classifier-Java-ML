# 🦁 Zoo Classifier (Java + Machine Learning) for study only

โปรแกรมจำแนกประเภทสัตว์โดยใช้ AI (LMT Algorithm) พัฒนาด้วย Java Swing และ Weka Library

## 📊 Model Performance
- **Algorithm:** Logistic Model Tree (LMT)
- **Accuracy:** 96.03%
- **Attributes:** 16 Features (Hair, Eggs, Milk, etc.)

## 🛠️ Requirements
- **Java JDK 8 หรือสูงกว่า**
- **Weka Library** (รวมอยู่ในโฟลเดอร์ `/lib` แล้ว)

## 🚀 How to Run
1. เพิ่ม `weka.jar` ในโปรเจกต์ของคุณ (Build Path)
2. ตรวจสอบให้แน่ใจว่าโฟลเดอร์ `model/` และ `image/` อยู่ในตำแหน่งเดียวกับไฟล์โปรแกรม
3. รันไฟล์ `zooWithGUI.java`

## 🧩 Key Logic
โปรแกรมจะอ่านค่าจาก GUI และสร้าง `DenseInstance` เพื่อส่งให้ Model ทำนายผลลัพธ์ พร้อมแสดงรูปภาพสัตว์ตามประเภทที่ทำนายได้

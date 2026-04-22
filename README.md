# 🛣️ Road Repair and Tracking System (RRTS)

A Java-based desktop application developed to automate and digitize the road repair workflow of a Public Works Department (PWD).

---

## 📌 Project Overview

The Road Repair and Tracking System (RRTS) is designed to replace manual paperwork used in municipal road maintenance. It enables real-time tracking of road repair requests, priority-based scheduling, resource management, and statistical reporting.

The system ensures transparency, automation, and efficient resource utilization.

---

## 🚀 Key Features

✅ Role-Based Authentication (Clerk, Supervisor, Admin, Mayor)  
✅ Priority-Based Auto Scheduling Algorithm  
✅ Real-Time Status Tracking (Pending → Scheduled → Completed)  
✅ Resource Management (Manpower & Machines)  
✅ Automatic Resource Release After Completion  
✅ Secure Password Storage (SHA-256 Hashing)  
✅ MVC Architecture Implementation  
✅ MySQL Database Integration  

---

## 🏗️ System Architecture

The project follows **MVC (Model-View-Controller)** architecture:
User → View → Controller → DAO → Database
↑________________________↓
Model
- **Model** → Represents data entities  
- **View** → Java Swing GUI  
- **Controller** → Business Logic  
- **DAO** → Database Interaction Layer  

---

## 🛠️ Tech Stack

| Layer | Technology |
|--------|------------|
| Frontend | Java Swing |
| Backend | Java |
| Database | MySQL |
| Connectivity | JDBC |
| Architecture | MVC |
| Security | SHA-256 Password Hashing |

---

## 👥 User Roles & Dashboards

| Role | Responsibilities |
|------|------------------|
| Clerk | Create repair requests |
| Supervisor | Assess severity, auto-schedule, mark completed |
| Admin | Manage resources (add/view/update) |
| Mayor | View repair statistics |

---

## 🔄 Road Lifecycle
PENDING → SCHEDULED → COMPLETED

- Clerk registers complaint  
- Supervisor sets severity  
- System calculates priority  
- Auto scheduling executed  
- Completion updates timestamp  
- Resources automatically released  

---

## 🧠 Scheduling Algorithm

Severity-to-Priority Mapping:

- CRITICAL → 4  
- HIGH → 3  
- MEDIUM → 2  
- LOW → 1  

Requests are sorted using Java Comparator (descending priority order).  
Higher priority roads are scheduled first.

---

## 🗄️ Database Schema

Main Tables:

- `users`
- `repair_requests`
- `resources`
- `schedules`

Example fields in `repair_requests`:

- rid  
- road_name  
- suburb  
- severity  
- priority  
- status  
- completion_date  

---

## 🔐 Security Implementation

- Passwords stored using SHA-256 hashing  
- PreparedStatement used to prevent SQL injection  
- Role-based access control enforced  

---

## ⚙️ Installation & Setup

### 1️⃣ Clone Repository

```bash
git clone https://github.com/yourusername/rrts.git

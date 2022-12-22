
<style>
    #nav-link{
        color: white;
    }
</style>



<nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
               
                <a href="dashboard.php" class="">
                  Dashboard
                </a>
           
                <div class="navbar-nav align-items-center ms-auto">
                <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                           
                            <span class=" d-lg-inline-flex "id="nav-link">Store</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-secondary border-0 rounded-0 rounded-bottom m-0">
                            <a href="items.php" class="dropdown-item">
                                <h6 class=" ">Items</h6>
                            </a>
                            
                            <hr class="dropdown-divider">
                            <a href="drugs.php" class="dropdown-item">
                                <h6 >Drugs</h6>
                            </a>
                           
                            <hr class="dropdown-divider">
                        </div>
                    </div>
                        
                <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                           
                            <span class=" d-lg-inline-flex "id="nav-link">Staff</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-secondary border-0 rounded-0 rounded-bottom m-0">
                            <a href="staff.php" class="dropdown-item">
                                <h6 class=" ">Staff</h6>
                            </a>
                            <a href="doctor.php" class="dropdown-item">
                                <h6 class=" ">Doctors</h6>
                            </a>
                            <hr class="dropdown-divider">
                            <a href="add_staff.php" class="dropdown-item">
                                <h6 >Add staff</h6>
                            </a>
                            <a href="add_doctor.php" class="dropdown-item">
                                <h6 >Add doctor</h6>
                            </a>
                            <hr class="dropdown-divider">
                        </div>
                    </div>
                     
                        <a href="approved_clients.php" class="nav-link " >
                           <span class="d-none d-lg-inline-flex" id="nav-link">Clients</span>
                        </a>
                     
                        <a href="elders_pending_approval.php" class="nav-link " >
                           <span class="d-none d-lg-inline-flex" id="nav-link">Elders</span>
                        </a>
                     
                        <a href="donors_approved.php" class="nav-link " >
                           <span class="d-none d-lg-inline-flex" id="nav-link">Donors</span>
                        </a>
                        <a href="pending_suppliers.php" class="nav-link " >
                           <span class="d-none d-lg-inline-flex" id="nav-link">Suppliers</span>
                        </a>
                        <a href="appointments_pending.php" class="nav-link " >
                           <span class="d-none d-lg-inline-flex" id="nav-link">Appointments</span>
                        </a>
                        <a href="donated_items.php" class="nav-link " >
                           <span class="d-none d-lg-inline-flex" id="nav-link">Donations</span>
                        </a>
                     
                        <a href="items_request.php" class="nav-link " >
                           <span class="d-none d-lg-inline-flex" id="nav-link">Item request</span>
                        </a>
                  
                  
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                         
                            <span class="d-none d-lg-inline-flex" id="nav-link">Admin</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                            
                            <a href="logout.php" class="dropdown-item" id="nav-link">Log out</a>
                        </div>
                    </div>
                </div>
            </nav>
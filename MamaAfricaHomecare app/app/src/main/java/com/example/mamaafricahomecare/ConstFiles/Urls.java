package com.example.mamaafricahomecare.ConstFiles;

public class Urls {
 public static String ipAddress = "http://ip_address_here/mama_africa/";
    private static final String ROOT_URL =ipAddress+ "user_apis/";

    public static final String URL_ITEMS_REQUESTED= ROOT_URL+"request_items.php";
    public static final String URL_ORDER_ITEMS= ROOT_URL+"order_items.php";
    public static final String URL_UPDATE_ORDER= ROOT_URL+"update_order.php";
    public static final String URL_RESET_PASSWORD= ROOT_URL+"reset_password.php";
    // user
    public static final String URL_REGISTER = ROOT_URL +"client/register.php";
    public static final String URL_LOGIN= ROOT_URL+"client/login.php";
    public static final String URL_ADD_ELDER= ROOT_URL+"client/add_elder.php";
    public static final String URL_GET_ELDERS= ROOT_URL+"client/get_elder.php";
    public static final String URL_HOSPITAL_APPOINTMENTS= ROOT_URL+"client/hospital_appointments.php";
    public static final String URL_GET_FEEDBACK= ROOT_URL+"client/get_feedback.php";
    public static final String URL_SEND_FEEDBACK= ROOT_URL+"client/send_feedback.php";
    public static final String URL_REQUEST= ROOT_URL+"client/items_request.php";
    //Staff
    public static final String URL_LOGIN_STAFF= ROOT_URL+"staff/login_staff.php";
    //Supervisor
    public static final String URL_GET_NEW_ELDERS= ROOT_URL+"staff/supervisor/new_elders.php";
    public static final String APPROVE_ELDER= ROOT_URL+"staff/supervisor/approve_elder.php";
    public static final String URL_GET_APPROVED_ELDERS= ROOT_URL+"staff/supervisor/elders_approved.php";
    public static final String URL_GET_CARE_GIVER= ROOT_URL+"staff/supervisor/care_givers.php";
    public static final String ASSIGN_CARE_GIVER= ROOT_URL+"staff/supervisor/assign_care_giver.php";
    public static final String URL_ADMITTED= ROOT_URL+"staff/supervisor/elders_admitted.php";
    public static final String URL_CARE_GIVER_ASSIGNED= ROOT_URL+"staff/supervisor/care_giver_assigned.php";
    //Care giver
    public static final String URL_ASSIGNED= ROOT_URL+"staff/care_giver/assigned.php";
    public static final String URL_GET_DOCTORS= ROOT_URL+"staff/care_giver/doctors.php";
    public static final String ASSIGN_BOOK_APPOINTMENT= ROOT_URL+"staff/care_giver/book_appointment.php";
    public static final String URL_APPOINTMENTS= ROOT_URL+"staff/care_giver/appointments.php";
    public static final String URL_GET_APPOINTMENT_DOCTOR= ROOT_URL+"staff/care_giver/appointment_doctor.php";
    public static final String URL_GET_TEST_DONE= ROOT_URL+"staff/care_giver/test_done.php";
    public static final String URL_GET_APPOINTMENT_DRUGS= ROOT_URL+"staff/care_giver/appointment_drugs.php";
    public static final String URL_REQUEST_CART= ROOT_URL+"staff/care_giver/request_cart.php";
    public static final String URL_AVAILABLE_ITEMS= ROOT_URL+"staff/care_giver/available_items.php";
    public static final String URL_ADD_TO_BASKET= ROOT_URL+"staff/care_giver/add_to_basket.php";
    public static final String URL_BASKET= ROOT_URL+"staff/care_giver/basket.php";
    public static final String URL_SEND_REQUEST= ROOT_URL+"staff/care_giver/send_request.php";
    public static final String URL_GET_REQUESTS= ROOT_URL+"staff/care_giver/requests.php";
    public static final String URL_FEED= ROOT_URL+"staff/care_giver/feed.php";
    public static final String URL_FEEDBACK= ROOT_URL+"staff/care_giver/feedback.php";
    public static final String URL_REPLY_FEEDBACK= ROOT_URL+"staff/care_giver/reply_feedback.php";
    //DOCS
    public static final String URL_BOOKINGS= ROOT_URL+"staff/doctor/booking.php";
    public static final String URL_APPROVE_BOOKING= ROOT_URL+"staff/doctor/approve_booking.php";
    public static final String URL_SEND_TO_LAB= ROOT_URL+"staff/doctor/send_to_lab.php";
    public static final String URL_LAB_TEST= ROOT_URL+"staff/doctor/lab_tests.php";
    public static final String URL_DRUGS= ROOT_URL+"staff/doctor/drugs.php";
    public static final String URL_ADD= ROOT_URL+"staff/doctor/add_drug.php";
    public static final String URL_CART= ROOT_URL+"staff/doctor/cart.php";
    public static final String URL_SEND_REPORT= ROOT_URL+"staff/doctor/send_report.php";
    public static final String URL_HISTORY= ROOT_URL+"staff/doctor/history.php";

    //PHARM
    public static final String URL_DRUGS_PENDING= ROOT_URL+"staff/pharmacist/drugs_pending.php";
    public static final String URL_ISSUE_DRUGS= ROOT_URL+"staff/pharmacist/issue_drug.php";
    public static final String URL_COLLECTED_DRUGS= ROOT_URL+"staff/pharmacist/drugs_collected.php";

    //LAB
    public static final String URL_TEST= ROOT_URL+"staff/labtech/tests.php";
    public static final String URL_SEND_RESULTS= ROOT_URL+"staff/labtech/send_results.php";

    //DONOR
    public static final String URL_REGISTER_DONOR= ROOT_URL+"donor/register_donor.php";
    public static final String URL_LOGIN_DONOR= ROOT_URL+"donor/login_donor.php";
    public static final String URL_DONATIONS= ROOT_URL+"donor/donations.php";
    public static final String URL_ITEMS= ROOT_URL+"donor/item_name.php";
    public static final String URL_ADD_ITEM= ROOT_URL+"donor/send_to_busket.php";
    public static final String URL_DONATED_BASKETS= ROOT_URL+"donor/donation_basket.php";
    public static final String URL_DONATE= ROOT_URL+"donor/submit_donation.php";
    public static final String URL_DONATED_ITEMS= ROOT_URL+"donor/donated_items.php";
    public static final String URL_DELIVER= ROOT_URL+"donor/deliver.php";

    //STORE MANAGER
    public static final String URL_GET_DONATIONS= ROOT_URL+"staff/store/donations.php";
    public static final String URL_ITEMS_DONATED= ROOT_URL+"staff/store/items_donated.php";
    public static final String URL_APPROVE_DONATION= ROOT_URL+"staff/store/approve_donation.php";
    public static final String URL_CONFIRM_DONATION= ROOT_URL+"staff/store/confirm_donation.php";
    public static final String URL_ITEMS_STOCK= ROOT_URL+"staff/store/stock.php";
    public static final String URL_APPROVE_REQUEST= ROOT_URL+"staff/store/approve_request.php";
    public static final String URL_REQUESTED= ROOT_URL+"staff/store/requested.php";
    public static final String URL_ISSUE_REQUEST= ROOT_URL+"staff/store/issued_request.php";
    public static final String URL_DRUG_STOCK= ROOT_URL+"staff/store/drugs_stock.php";
    public static final String URL_SUPPLIERS= ROOT_URL+"staff/store/suppliers.php";
    public static final String URL_SEND_TO_ORDER_CART= ROOT_URL+"staff/store/sent_to_order_cart.php";
    public static final String URL_SEND_ORDER= ROOT_URL+"staff/store/send_order.php";
    public static final String URL_ORDER_BASKET= ROOT_URL+"staff/store/order_basket.php";
    public static final String URL_ORDER_REQUEST= ROOT_URL+"staff/store/order_request.php";

    //SUPPLIER
    public static final String URL_REGISTER_SUPPLIER= ROOT_URL+"supplier/register_suppliers.php";
    public static final String URL_LOGIN_SUPPLIER= ROOT_URL+"supplier/login_supplier.php";
    public static final String URL_MY_ORDERS= ROOT_URL+"supplier/my_orders.php";
}

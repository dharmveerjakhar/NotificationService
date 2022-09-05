package com.notificationservice.constants;

public interface Constants {

    interface Endpoint {

        String BASE_PATH = "/v1";
        String SEND_SMS = "/sms/send";
        String GET_SMS_REQUEST = "/sms/get_sms_request_by_id";
        String GET_SMS_REQUEST_WITH_ID_PARAM = "/sms/get_sms_request_by_id/{reqId}";
        String BLACKLIST_CRUD = "/blacklist";
        String GET_ALL_SMS_REQUEST_BY_MESSAGE_CONTAINING_TEXT = "/sms/get_by_message_containing_text";
        String GET_ALL_SMS_REQUEST_BY_PHONE_NUMBER_IN_TIME_WINDOW = "/sms/get_by_phone_number_in_time_window";
    }

    interface Auth {

        String ANT_PATTERN = "/v1/**";
    }

    interface Config {

        String CONNECTION_TIMEOUT = "5000";
        String READ_TIMEOUT = "5000";
    }

    interface ImiApi {

        String API_URL = "https://api.imiconnect.in/resources/v1/messaging";
        String API_KEY = "";

    }

    String KAFKA_TOPIC = "send_sms";
    String KAFKA_GROUP_ID = "group_id";

}

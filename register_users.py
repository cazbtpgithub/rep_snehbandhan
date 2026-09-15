import requests

URL = "http://localhost:80/registrationLogin"

def register_user(i):
    data = {
        "materialStatusPartner": "1",
        "physicalStatusPartner": "1",
        "bodyTypePartner": "1",
        "complexionPartner": "1",
        "heightSelectPartnerFirst": "1",
        "heightSelectPartnerSecond": "1",
        "countrySelectPartner": "1",
        "stateSelectPartner": "1",
        "cityStateSelectPartnerPreferance": "1",
        "religionSelectPartner": "1",
        "casteSelectPartner": "1",
        "checkBoxEducationPartner": "1",
        "checkBoxOccupationPartner": "1",
        "incomePartner": "1",
        "incomeAmountPartner": "1000",
        "familyClassInfoPartner": "1",
        "familyTypeInfoPartner": "1",
        "familyValueInfoPartner": "1",
        "eatingPartner": "1",
        "drinkPartner": "1",
        "smokePartner": "1",
        "aboutMe": "I am a good person",
        "materialStatus": "1",
        "physicalStatus": "1",
        "heightSelect": "1",
        "weightSelect": "1",
        "countrySelect": "1",
        "stateSelect": "1",
        "cityStateSelect": "1",
        "subDistSelect": "1",
        "religionSelect": "1",
        "casteSelect": "1",
        "educationalCategorySelect": "1",
        "bodyType": "1",
        "complexion": "1",
        "educationalSelect": "1",
        "occupationCategorySelect": "1",
        "occupationSelect": "1",
        "income": "1",
        "incomeAmount": "1000",
        "familyClassInfo": "1",
        "familyTypeInfo": "1",
        "familyValueInfo": "1",
        "eating": "1",
        "drink": "1",
        "smoke": "1",
        "firstName": f"TestUser{i}",
        "gender": "Male",
        "mobile": f"98765432{i:02d}",
        "dob": "01/01/1990",
        "userName": f"testuser{i}",
        "password": "password123",
        "email": f"testuser{i}@example.com",
        "employedIn": "1",
        "employedInPartner": "1",
        "ProfileCreatedFor": "Self",
        "kitUser": "NoKit",
        "kitId": "",
        "kitPassword": "",
        "ladmark": "Near Park",
        "kundaliImg": ""
    }
    
    response = requests.post(URL, data=data)
    print(f"User {i} Registration - Status Code: {response.status_code}")
    if response.status_code == 200:
        print(f"Response: {response.text}")
    else:
        print(f"Failed: {response.text}")

if __name__ == "__main__":
    for i in range(1, 6):
        register_user(i)

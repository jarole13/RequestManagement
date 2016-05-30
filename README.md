# RequestManagement

# Rests endpoints
Rest web service can be found in package com.bluemedia.app.rest

# Implementation
Implementation has been created in package com.bluemedia.app.impl

#Validation
Validation has been done by custom constraints(@ReasonValidity, @StatusValidity, @ContentChangeAllowedValiditiy) and constraints made available by framework.

#Request history
History of request status changes is stored in RequestHistoryEntity. New record is created only in case, when status has been changed.

#Unique number
Unique number is encoded current DATE, thus it's not going to be repeated.

#TESTS
I've tried to test everything what was implemented. However due to late night I could miss to test some cases.

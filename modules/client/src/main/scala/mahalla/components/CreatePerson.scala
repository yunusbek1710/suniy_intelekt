package mahalla.components

import cats.implicits.catsSyntaxOptionId
import district.api.Urls.AdminData
import district.domain.DocumentType.{BIRTHCERTIFICATE, DEFAULT, IDCARD, PASSPORT}
import district.domain.Gender
import district.domain.Gender.{FEMALE, MALE}
import domain.PersonParams
import mahalla.mask.InputMask
import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.facade.SyntheticEvent
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage
import mahalla.notification.Notification
import org.scalajs.dom.{HTMLInputElement, HTMLSelectElement}

object CreatePerson extends AjaxImplicits {

  case class State(
    busyLoader: Boolean = false,
    personParams: PersonParams = PersonParams()
  )

  class Backend($ : Hooks.UseState[State]) {

    def onChangeDocumentType(e: SyntheticEvent[HTMLSelectElement]): Callback =
      $.modState(
        _.copy(personParams =
          $.value.personParams.copy(documentType =
            if (e.target.value == "id_card") IDCARD
            else if (e.target.value == "birth_certificate") BIRTHCERTIFICATE
            else if (e.target.value == "passport") PASSPORT
            else DEFAULT
          )
        )
      )

    def onChangeGender(e: SyntheticEvent[HTMLSelectElement]): Callback =
      $.modState(
        _.copy(personParams =
          $.value.personParams.copy(gender =
            if (e.target.value == "male") MALE
            else if (e.target.value == "female") FEMALE
            else Gender.DEFAULT
          )
        )
      )

    def onChangeEmployment(e: SyntheticEvent[HTMLSelectElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(employmentStatus = e.target.value)))

    def onChangeBirthday(e: SyntheticEvent[HTMLSelectElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(birthday = e.target.value)))

    def onChangeYouthNote(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(youthNote = e.target.value)))

    def onChangeIronNote(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(ironNote = e.target.value)))

    def onChangeWomenNote(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(womenNote = e.target.value)))

    def onChangeFamilyStatus(e: SyntheticEvent[HTMLSelectElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(familyStatus = e.target.value)))

    def onChangeHealthStatus(e: SyntheticEvent[HTMLSelectElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(healthStatus = e.target.value)))

    def onChangeDocumentNumber(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(documentNumber = e.target.value)))

    def onChangePhoneNumber(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(phoneNumber = e.target.value.some)))

    def onChangeFirstName(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(firstName = e.target.value)))

    def onChangeLastName(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(lastName = e.target.value)))

    def onChangeFathersName(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(fathersName = e.target.value)))

    def onChangeEducationalInfo(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(educationalInfo = e.target.value)))

    def onChangeStreet(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(street = e.target.value)))

    def onChangeHouseNumber(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(houseNumber = e.target.value.toInt)))

    def onChangeLivingPlace(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(personParams = $.value.personParams.copy(livingPlace = e.target.value)))

    def addPersonInfo: Callback =
      if ($.value.personParams.documentType == DEFAULT)
        Notification.error("Please select your document type!")
      else if ($.value.personParams.documentNumber.isEmpty)
        Notification.error("Please enter your document number!")
      else if ($.value.personParams.birthday.isEmpty)
        Notification.error("Please enter your birthday!")
      else if ($.value.personParams.firstName.isEmpty)
        Notification.error("Please enter your firstname!")
      else if ($.value.personParams.lastName.isEmpty)
        Notification.error("Please enter your lastname!")
      else if ($.value.personParams.fathersName.isEmpty)
        Notification.error("Please enter your father's name!")
      else if ($.value.personParams.livingPlace.isEmpty)
        Notification.error("Please enter your living place!")
      else if ($.value.personParams.gender == Gender.DEFAULT)
        Notification.error("Please select your gender!")
      else if ($.value.personParams.street.isEmpty)
        Notification.error("Please enter your street!")
      else if ($.value.personParams.houseNumber == 0)
        Notification.error("Please enter your house number!")
      else if ($.value.personParams.employmentStatus.isEmpty)
        Notification.error("Please enter your employment status!")
      else if ($.value.personParams.educationalInfo.isEmpty)
        Notification.error("Please enter your edu status!")
      else if ($.value.personParams.familyStatus.isEmpty)
        Notification.error("Please enter your family status!")
      else if ($.value.personParams.healthStatus.isEmpty)
        Notification.error("Please enter your health status!")
      else if ($.value.personParams.youthNote.isEmpty)
        Notification.error("Please select your youth note!")
      else if ($.value.personParams.ironNote.isEmpty)
        Notification.error("Please select your iron note!")
      else if ($.value.personParams.womenNote.isEmpty)
        Notification.error("Please select your women note!")
      else
        $.modState(_.copy(busyLoader = true)) >> {
          println($.value.personParams)
          post[PersonParams](url = AdminData.add, $.value.personParams)
            .fail(onError)
            .doneWithoutContent {
              Notification.success("Person info added!") >>
                $.modState(_.copy(personParams = PersonParams(), busyLoader = false))
            }
            .asCallback
        }

    def account: VdomArray =
      VdomArray(
        <.div(^.className := "container my-5")(
          <.div(^.cls := "d-flex justify-content-center")(
            <.div(^.cls := "person-form")(
              <.h3(^.className := "text-center")("Shaxsiy ma'lumot kiritish"),
              <.div(^.className := "row mt-4")(
                <.div(^.className := "col-md-3")(
                  <.label(^.`for` := "inputDoc", "Hujjat turi"),
                  <.select(
                    ^.onChange ==> onChangeDocumentType,
                    ^.id        := "inputDoc",
                    ^.className := "form-control")(
                    <.option(^.value := "")("Select..."),
                    <.option(^.value := "passport")("Passport"),
                    <.option(^.value := "id_card")("ID karta"),
                    <.option(^.value := "birth_certificate")("Tug'ilganlik guvohnomasi")
                  )
                ),
                <.div(^.className := "col-md-3")(
                  <.label(^.`for` := "docnumber", "Hujjat seriyasi va raqami"),
                  <.input(
                    ^.`type`    := "text",
                    ^.className := "form-control form-control-lg",
                    ^.value     := $.value.personParams.documentNumber,
                    ^.onChange ==> onChangeDocumentNumber
                  )
                ),
                <.div(^.className := "col-md-3")(
                  <.label(^.`for` := "docnumber", "Tug'ilgan kuni"),
                  InputMask(
                    className = "form-control form-control-lg",
                    `type` = "text",
                    value     = $.value.personParams.birthday,
                    onChange = onChangeBirthday,
                    mask = "99-99-9999",
                    placeholder = "kun-oy-yil"
                  )
                ),
                <.div(^.className := "form-group col-md-3")(
                  <.label(^.`for` := "phonenumber", "Telefon raqam"),
                  InputMask(
                    className = "form-control form-control-lg",
                    `type` = "text",
                    id = "phonenumber",
                    value     = $.value.personParams.phoneNumber.fold("")(_.trim),
                    onChange = onChangePhoneNumber,
                    mask = "99-999-9999",
                    placeholder = "+998"
                  )
                )
              ),
              <.div(^.className := "row")(
                <.div(^.className := "col")(
                  <.label(^.`for` := "firstname", "Ism"),
                  <.input(
                    ^.`type`    := "text",
                    ^.className := "form-control form-control-lg",
                    ^.value     := $.value.personParams.firstName,
                    ^.onChange ==> onChangeFirstName
                  )
                ),
                <.div(^.className := "col")(
                  <.label(^.`for` := "lastname", "Familiya"),
                  <.input(
                    ^.`type`    := "text",
                    ^.className := "form-control form-control-lg",
                    ^.value     := $.value.personParams.lastName,
                    ^.onChange ==> onChangeLastName
                  )
                ),
                <.div(^.className := "col")(
                  <.label(^.`for` := "fathersname", "Otasining ismi"),
                  <.input(
                    ^.`type`    := "text",
                    ^.className := "form-control form-control-lg",
                    ^.value     := $.value.personParams.fathersName,
                    ^.onChange ==> onChangeFathersName
                  )
                )
              ),
              <.div(^.className := "row mt-3")(
                <.div(^.className := "form-group col-md-6")(
                  <.label(^.`for` := "inputAdress", "Manzil"),
                  <.input(
                    ^.`type`    := "text",
                    ^.className := "form-control form-control-lg",
                    ^.id        := "inputAdress",
                    ^.value     := $.value.personParams.livingPlace,
                    ^.onChange ==> onChangeLivingPlace
                  )
                ),
                <.div(^.className := "form-group col-md-3")(
                  <.label(^.`for` := "street", "Ko'cha"),
                  <.input(
                    ^.`type`    := "text",
                    ^.className := "form-control form-control-lg",
                    ^.id        := "street",
                    ^.value     := $.value.personParams.street,
                    ^.onChange ==> onChangeStreet
                  )
                ),
                <.div(^.className := "form-group col-md-3")(
                  <.label(^.`for` := "housenumber", "Uy raqami"),
                  <.input(
                    ^.`type`    := "number",
                    ^.className := "form-control form-control-lg",
                    ^.id        := "housenumber",
                    ^.value     := $.value.personParams.houseNumber,
                    ^.onChange ==> onChangeHouseNumber
                  )
                )
              ),
              <.div(^.className := "row")(
                <.div(^.className := "col-md-3")(
                  <.label(^.`for` := "gender")("Jinsi"),
                  <.select(
                    ^.onChange ==> onChangeGender,
                    ^.id        := "gender",
                    ^.className := "form-control")(
                    <.option(^.value := "")("Select..."),
                    <.option(^.value := "male")("Erkak"),
                    <.option(^.value := "female")("Ayol")
                  )
                ),
                <.div(^.className := "col-md-3")(
                  <.label(^.`for` := "employment", "Bandlik holati"),
                  <.select(
                    ^.id := "employment",
                    ^.onChange ==> onChangeEmployment,
                    ^.className := "form-control")(
                    <.option(^.value := "")("Select..."),
                    <.option(^.value := "Rasmiy band")("Rasmiy band"),
                    <.option(^.value := "Migratsiyaga ketgan")("Migratsiyaga ketgan"),
                    <.option(^.value := "Norasmiy band")("Norasmiy band"),
                    <.option(^.value := "Ishlash istagi yo'q")("Ishlash istagi yo'q"),
                    <.option(^.value := "Mehnatga layoqatsiz")("Mehnatga layoqatsiz"),
                    <.option(^.value := "Ishsiz yosh")("Ishsiz yosh"),
                    <.option(^.value := "Ta'limda rasmiy band")("Ta'limda rasmiy band"),
                    <.option(^.value := "Harbiy xizmatda")("Harbiy xizmatda"),
                    <.option(^.value := "Nafaqada")("Nafaqada")
                  )
                ),
                <.div(^.className := "col-md-3")(
                  <.label(^.`for` := "familyCondition", "Oilaviy ahvoli"),
                  <.select(
                    ^.onChange ==> onChangeFamilyStatus,
                    ^.id        := "familyCondition",
                    ^.className := "form-control")(
                    <.option(^.value := "")("Select..."),
                    <.option(^.value := "Oilali")("Oilali"),
                    <.option(^.value := "Ajrashgan")("Ajrashgan"),
                    <.option(^.value := "Oila qurmagan")("Oila qurmagan"),
                    <.option(^.value := "Beva")("Beva"),
                    <.option(^.value := "Boquvchisini yo'qotgan")("Boquvchisini yo'qotgan")
                  )
                ),
                <.div(^.className := "col-md-3")(
                  <.label(^.`for` := "health", "Salomatlik holati"),
                  <.select(
                    ^.onChange ==> onChangeHealthStatus,
                    ^.id        := "health",
                    ^.className := "form-control")(
                    <.option(^.value := "")("Select..."),
                    <.option(^.value := "Sog'lom")("Sog'lom"),
                    <.option(^.value := "Surunkali kasallikka chalingan")("Surunkali kasallikka chalingan"),
                    <.option(^.value := "Nogironligi bor I")("Nogironligi bor I"),
                    <.option(^.value := "Nogironligi bor II")("Nogironligi bor II"),
                    <.option(^.value := "Nogironligi bor III")("Nogironligi bor III")
                  )
                )
              ),
              <.h5(^.className := "text-center mt-5", "Ta'lim holati"),
              <.div(^.className := "row m-3")(
                <.div(^.className := "col-md-3")(
                  <.div(^.className := "form-check")(
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.name      := "edutypes",
                      ^.id        := "kindergarten",
                      ^.value     := "option1"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "kindergarten")("Maktabgacha ta'lim")
                  ),
                  <.div(^.className := "form-check")(
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.name      := "edutypes",
                      ^.id        := "school",
                      ^.value     := "option2"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "school")("Umumiy o'rta ta'lim")
                  ),
                  <.div(^.className := "form-check")(
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.name      := "edutypes",
                      ^.id        := "preoeducation",
                      ^.value     := "option2"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "preoeducation", "Professional ta'lim")
                  ),
                  <.div(^.className := "form-check")(
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.name      := "edutypes",
                      ^.id        := "higheducation",
                      ^.value     := "option2"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "higheducation", "Oliy ta'lim")
                  ),
                  <.div(^.className := "form-check")(
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.name      := "edutypes",
                      ^.id        := "magistr",
                      ^.value     := "option2"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "magistr", "Oliy ta'limdan keyingi ta'lim")
                  )
                ),
                <.div(^.className := "col-md-3")(
                  <.div(
                    ^.className := "form-check",
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.name      := "edustatus",
                      ^.id        := "graduated",
                      ^.value     := "option1"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "graduated", "Tugallangan")
                  ),
                  <.div(
                    ^.className := "form-check",
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.name      := "edustatus",
                      ^.id        := "ungraduated",
                      ^.value     := "option2"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "ungraduated", "Tugallanmagan")
                  ),
                  <.hr,
                  <.div(
                    ^.className := "form-check",
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.name      := "etype",
                      ^.id        := "state",
                      ^.value     := "option1",
                      ^.checked   := true
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "state", "Davlat muassasasi")
                  ),
                  <.div(
                    ^.className := "form-check",
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.name      := "etype",
                      ^.id        := "private",
                      ^.value     := "option2"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "private", "Xususiy muassasasi")
                  )
                ),
                <.div(
                  ^.className := "col-md-6",
                  <.label(^.`for` := "housenumber")("Ta'lim muassasasi nomi"),
                  <.input(
                    ^.`type`    := "text",
                    ^.className := "form-control form-control-lg",
                    ^.id        := "housenumber",
                    ^.onChange ==> onChangeEducationalInfo,
                    ^.value := $.value.personParams.educationalInfo
                  )
                )
              ),
              <.div(^.className := "row m-3 mt-5")(
                <.div(^.className := "col-md-4")(
                  <.h5(^.className := "mb-3")("Yoshlar daftariga kirganmi?"),
                  <.div(^.className := "ml-5 form-check")(
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.onChange ==> onChangeYouthNote,
                      ^.name  := "youthnote",
                      ^.id    := "yes",
                      ^.value := "ha"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "yes", "HA")
                  ),
                  <.div(
                    ^.className := "ml-5 form-check",
                    <.input(
                      ^.className := "form-check-input",
                      ^.onChange ==> onChangeYouthNote,
                      ^.`type` := "radio",
                      ^.name   := "youthnote",
                      ^.id     := "no",
                      ^.value  := "yo'q"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "no", "YO'Q")
                  )
                ),
                <.div(
                  ^.className := "col-md-4",
                  <.h5(^.className := "mb-3", "Temir daftariga kirganmi?"),
                  <.div(
                    ^.className := "ml-5 form-check",
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.onChange ==> onChangeIronNote,
                      ^.name  := "ironnote",
                      ^.id    := "yes",
                      ^.value := "ha"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "yes", "HA")
                  ),
                  <.div(
                    ^.className := "ml-5 form-check",
                    <.input(
                      ^.className := "form-check-input",
                      ^.onChange ==> onChangeIronNote,
                      ^.`type` := "radio",
                      ^.name   := "ironnote",
                      ^.id     := "no",
                      ^.value  := "yo'q"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "no", "YO'Q")
                  )
                ),
                <.div(^.className := "col-md-4")(
                  <.h5(^.className := "mb-3")("Ayollar daftariga kirganmi?"),
                  <.div(^.className := "ml-5 form-check")(
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.onChange ==> onChangeWomenNote,
                      ^.name  := "womennote",
                      ^.id    := "yes",
                      ^.value := "ha"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "yes", "HA")
                  ),
                  <.div(
                    ^.className := "ml-5 form-check",
                    <.input(
                      ^.className := "form-check-input",
                      ^.`type`    := "radio",
                      ^.onChange ==> onChangeWomenNote,
                      ^.name  := "womennote",
                      ^.id    := "no",
                      ^.value := "yo'q"
                    ),
                    <.label(^.className := "form-check-label", ^.`for` := "no", "YO'Q")
                  )
                )
              ),
              <.div(^.className := "row m-5")(
                <.div(^.className      := "col text-center")(
                  <.button(^.className := "btn btn-success btn-lg", ^.onClick --> addPersonInfo)("Saqlash")
                )
              )
            )
          )
        )
      )

    def render: VdomArray =
      VdomArray(
        <.div(
          <.div(^.cls := "main-container")(
            <.div(^.id := "content")(
              account
            )
          )
        )
      )

  }

  case class Props(ctl: RouterCtl[AppPage])

  val component: Component[Props, CtorType.Props] =
    ScalaFnComponent
      .withHooks[Props]
      .useState(State())
      .render { (_: Props, $ : Hooks.UseState[State]) =>
        new Backend($).render
      }
}

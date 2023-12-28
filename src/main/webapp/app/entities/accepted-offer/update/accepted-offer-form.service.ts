import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAcceptedOffer, NewAcceptedOffer } from '../accepted-offer.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAcceptedOffer for edit and NewAcceptedOfferFormGroupInput for create.
 */
type AcceptedOfferFormGroupInput = IAcceptedOffer | PartialWithRequiredKeyOf<NewAcceptedOffer>;

type AcceptedOfferFormDefaults = Pick<NewAcceptedOffer, 'id'>;

type AcceptedOfferFormGroupContent = {
  id: FormControl<IAcceptedOffer['id'] | NewAcceptedOffer['id']>;
  offerId: FormControl<IAcceptedOffer['offerId']>;
  acceptedOfferRefNo: FormControl<IAcceptedOffer['acceptedOfferRefNo']>;
  reqOffId: FormControl<IAcceptedOffer['reqOffId']>;
  value: FormControl<IAcceptedOffer['value']>;
  reqAmount: FormControl<IAcceptedOffer['reqAmount']>;
  marginPtg: FormControl<IAcceptedOffer['marginPtg']>;
  marginValue: FormControl<IAcceptedOffer['marginValue']>;
  amountAftMargin: FormControl<IAcceptedOffer['amountAftMargin']>;
  interestPtg: FormControl<IAcceptedOffer['interestPtg']>;
  term: FormControl<IAcceptedOffer['term']>;
  interestValue: FormControl<IAcceptedOffer['interestValue']>;
  netAmount: FormControl<IAcceptedOffer['netAmount']>;
  status: FormControl<IAcceptedOffer['status']>;
  offerDate: FormControl<IAcceptedOffer['offerDate']>;
  offerAcceptedDate: FormControl<IAcceptedOffer['offerAcceptedDate']>;
  financerequest: FormControl<IAcceptedOffer['financerequest']>;
  financepartner: FormControl<IAcceptedOffer['financepartner']>;
  anchortrader: FormControl<IAcceptedOffer['anchortrader']>;
};

export type AcceptedOfferFormGroup = FormGroup<AcceptedOfferFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AcceptedOfferFormService {
  createAcceptedOfferFormGroup(acceptedOffer: AcceptedOfferFormGroupInput = { id: null }): AcceptedOfferFormGroup {
    const acceptedOfferRawValue = {
      ...this.getFormDefaults(),
      ...acceptedOffer,
    };
    return new FormGroup<AcceptedOfferFormGroupContent>({
      id: new FormControl(
        { value: acceptedOfferRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      offerId: new FormControl(acceptedOfferRawValue.offerId),
      acceptedOfferRefNo: new FormControl(acceptedOfferRawValue.acceptedOfferRefNo),
      reqOffId: new FormControl(acceptedOfferRawValue.reqOffId, {
        validators: [Validators.required],
      }),
      value: new FormControl(acceptedOfferRawValue.value, {
        validators: [Validators.required],
      }),
      reqAmount: new FormControl(acceptedOfferRawValue.reqAmount, {
        validators: [Validators.required],
      }),
      marginPtg: new FormControl(acceptedOfferRawValue.marginPtg, {
        validators: [Validators.required],
      }),
      marginValue: new FormControl(acceptedOfferRawValue.marginValue, {
        validators: [Validators.required],
      }),
      amountAftMargin: new FormControl(acceptedOfferRawValue.amountAftMargin, {
        validators: [Validators.required],
      }),
      interestPtg: new FormControl(acceptedOfferRawValue.interestPtg, {
        validators: [Validators.required],
      }),
      term: new FormControl(acceptedOfferRawValue.term, {
        validators: [Validators.required],
      }),
      interestValue: new FormControl(acceptedOfferRawValue.interestValue, {
        validators: [Validators.required],
      }),
      netAmount: new FormControl(acceptedOfferRawValue.netAmount, {
        validators: [Validators.required],
      }),
      status: new FormControl(acceptedOfferRawValue.status, {
        validators: [Validators.required],
      }),
      offerDate: new FormControl(acceptedOfferRawValue.offerDate, {
        validators: [Validators.required],
      }),
      offerAcceptedDate: new FormControl(acceptedOfferRawValue.offerAcceptedDate, {
        validators: [Validators.required],
      }),
      financerequest: new FormControl(acceptedOfferRawValue.financerequest),
      financepartner: new FormControl(acceptedOfferRawValue.financepartner),
      anchortrader: new FormControl(acceptedOfferRawValue.anchortrader),
    });
  }

  getAcceptedOffer(form: AcceptedOfferFormGroup): IAcceptedOffer | NewAcceptedOffer {
    return form.getRawValue() as IAcceptedOffer | NewAcceptedOffer;
  }

  resetForm(form: AcceptedOfferFormGroup, acceptedOffer: AcceptedOfferFormGroupInput): void {
    const acceptedOfferRawValue = { ...this.getFormDefaults(), ...acceptedOffer };
    form.reset(
      {
        ...acceptedOfferRawValue,
        id: { value: acceptedOfferRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AcceptedOfferFormDefaults {
    return {
      id: null,
    };
  }
}

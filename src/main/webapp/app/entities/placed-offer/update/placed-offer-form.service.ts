import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IPlacedOffer, NewPlacedOffer } from '../placed-offer.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPlacedOffer for edit and NewPlacedOfferFormGroupInput for create.
 */
type PlacedOfferFormGroupInput = IPlacedOffer | PartialWithRequiredKeyOf<NewPlacedOffer>;

type PlacedOfferFormDefaults = Pick<NewPlacedOffer, 'id'>;

type PlacedOfferFormGroupContent = {
  id: FormControl<IPlacedOffer['id'] | NewPlacedOffer['id']>;
  placedOfferUlidId: FormControl<IPlacedOffer['placedOfferUlidId']>;
  placedOfferRefNo: FormControl<IPlacedOffer['placedOfferRefNo']>;
  reqOffUlidId: FormControl<IPlacedOffer['reqOffUlidId']>;
  requestOfferRefNo: FormControl<IPlacedOffer['requestOfferRefNo']>;
  value: FormControl<IPlacedOffer['value']>;
  reqAmount: FormControl<IPlacedOffer['reqAmount']>;
  marginPtg: FormControl<IPlacedOffer['marginPtg']>;
  marginValue: FormControl<IPlacedOffer['marginValue']>;
  amountAftMargin: FormControl<IPlacedOffer['amountAftMargin']>;
  interestPtg: FormControl<IPlacedOffer['interestPtg']>;
  term: FormControl<IPlacedOffer['term']>;
  interestValue: FormControl<IPlacedOffer['interestValue']>;
  netAmount: FormControl<IPlacedOffer['netAmount']>;
  status: FormControl<IPlacedOffer['status']>;
  offerDate: FormControl<IPlacedOffer['offerDate']>;
  placedOfferDate: FormControl<IPlacedOffer['placedOfferDate']>;
  anchorTrader: FormControl<IPlacedOffer['anchorTrader']>;
  tradePartner: FormControl<IPlacedOffer['tradePartner']>;
  disbursalAmount: FormControl<IPlacedOffer['disbursalAmount']>;
  financerequest: FormControl<IPlacedOffer['financerequest']>;
  financepartner: FormControl<IPlacedOffer['financepartner']>;
};

export type PlacedOfferFormGroup = FormGroup<PlacedOfferFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PlacedOfferFormService {
  createPlacedOfferFormGroup(placedOffer: PlacedOfferFormGroupInput = { id: null }): PlacedOfferFormGroup {
    const placedOfferRawValue = {
      ...this.getFormDefaults(),
      ...placedOffer,
    };
    return new FormGroup<PlacedOfferFormGroupContent>({
      id: new FormControl(
        { value: placedOfferRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      placedOfferUlidId: new FormControl(placedOfferRawValue.placedOfferUlidId),
      placedOfferRefNo: new FormControl(placedOfferRawValue.placedOfferRefNo),
      reqOffUlidId: new FormControl(placedOfferRawValue.reqOffUlidId),
      requestOfferRefNo: new FormControl(placedOfferRawValue.requestOfferRefNo),
      value: new FormControl(placedOfferRawValue.value, {
        validators: [Validators.required],
      }),
      reqAmount: new FormControl(placedOfferRawValue.reqAmount, {
        validators: [Validators.required],
      }),
      marginPtg: new FormControl(placedOfferRawValue.marginPtg, {
        validators: [Validators.required],
      }),
      marginValue: new FormControl(placedOfferRawValue.marginValue, {
        validators: [Validators.required],
      }),
      amountAftMargin: new FormControl(placedOfferRawValue.amountAftMargin, {
        validators: [Validators.required],
      }),
      interestPtg: new FormControl(placedOfferRawValue.interestPtg, {
        validators: [Validators.required],
      }),
      term: new FormControl(placedOfferRawValue.term, {
        validators: [Validators.required],
      }),
      interestValue: new FormControl(placedOfferRawValue.interestValue, {
        validators: [Validators.required],
      }),
      netAmount: new FormControl(placedOfferRawValue.netAmount, {
        validators: [Validators.required],
      }),
      status: new FormControl(placedOfferRawValue.status, {
        validators: [Validators.required],
      }),
      offerDate: new FormControl(placedOfferRawValue.offerDate, {
        validators: [Validators.required],
      }),
      placedOfferDate: new FormControl(placedOfferRawValue.placedOfferDate),
      anchorTrader: new FormControl(placedOfferRawValue.anchorTrader),
      tradePartner: new FormControl(placedOfferRawValue.tradePartner),
      disbursalAmount: new FormControl(placedOfferRawValue.disbursalAmount, {
        validators: [Validators.required],
      }),
      financerequest: new FormControl(placedOfferRawValue.financerequest),
      financepartner: new FormControl(placedOfferRawValue.financepartner),
    });
  }

  getPlacedOffer(form: PlacedOfferFormGroup): IPlacedOffer | NewPlacedOffer {
    return form.getRawValue() as IPlacedOffer | NewPlacedOffer;
  }

  resetForm(form: PlacedOfferFormGroup, placedOffer: PlacedOfferFormGroupInput): void {
    const placedOfferRawValue = { ...this.getFormDefaults(), ...placedOffer };
    form.reset(
      {
        ...placedOfferRawValue,
        id: { value: placedOfferRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): PlacedOfferFormDefaults {
    return {
      id: null,
    };
  }
}

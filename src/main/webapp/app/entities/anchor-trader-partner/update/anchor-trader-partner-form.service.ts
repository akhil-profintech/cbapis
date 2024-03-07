import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAnchorTraderPartner, NewAnchorTraderPartner } from '../anchor-trader-partner.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAnchorTraderPartner for edit and NewAnchorTraderPartnerFormGroupInput for create.
 */
type AnchorTraderPartnerFormGroupInput = IAnchorTraderPartner | PartialWithRequiredKeyOf<NewAnchorTraderPartner>;

type AnchorTraderPartnerFormDefaults = Pick<NewAnchorTraderPartner, 'id'>;

type AnchorTraderPartnerFormGroupContent = {
  id: FormControl<IAnchorTraderPartner['id'] | NewAnchorTraderPartner['id']>;
  atpartnerId: FormControl<IAnchorTraderPartner['atpartnerId']>;
  atpartnerUlidId: FormControl<IAnchorTraderPartner['atpartnerUlidId']>;
  pan: FormControl<IAnchorTraderPartner['pan']>;
  panStatus: FormControl<IAnchorTraderPartner['panStatus']>;
  name: FormControl<IAnchorTraderPartner['name']>;
  aadhar: FormControl<IAnchorTraderPartner['aadhar']>;
  aadharOtp: FormControl<IAnchorTraderPartner['aadharOtp']>;
  aadharStatus: FormControl<IAnchorTraderPartner['aadharStatus']>;
  aadharName: FormControl<IAnchorTraderPartner['aadharName']>;
  aadharAddress: FormControl<IAnchorTraderPartner['aadharAddress']>;
  anchortrader: FormControl<IAnchorTraderPartner['anchortrader']>;
};

export type AnchorTraderPartnerFormGroup = FormGroup<AnchorTraderPartnerFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AnchorTraderPartnerFormService {
  createAnchorTraderPartnerFormGroup(anchorTraderPartner: AnchorTraderPartnerFormGroupInput = { id: null }): AnchorTraderPartnerFormGroup {
    const anchorTraderPartnerRawValue = {
      ...this.getFormDefaults(),
      ...anchorTraderPartner,
    };
    return new FormGroup<AnchorTraderPartnerFormGroupContent>({
      id: new FormControl(
        { value: anchorTraderPartnerRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      atpartnerId: new FormControl(anchorTraderPartnerRawValue.atpartnerId),
      atpartnerUlidId: new FormControl(anchorTraderPartnerRawValue.atpartnerUlidId),
      pan: new FormControl(anchorTraderPartnerRawValue.pan),
      panStatus: new FormControl(anchorTraderPartnerRawValue.panStatus),
      name: new FormControl(anchorTraderPartnerRawValue.name),
      aadhar: new FormControl(anchorTraderPartnerRawValue.aadhar),
      aadharOtp: new FormControl(anchorTraderPartnerRawValue.aadharOtp),
      aadharStatus: new FormControl(anchorTraderPartnerRawValue.aadharStatus),
      aadharName: new FormControl(anchorTraderPartnerRawValue.aadharName),
      aadharAddress: new FormControl(anchorTraderPartnerRawValue.aadharAddress),
      anchortrader: new FormControl(anchorTraderPartnerRawValue.anchortrader),
    });
  }

  getAnchorTraderPartner(form: AnchorTraderPartnerFormGroup): IAnchorTraderPartner | NewAnchorTraderPartner {
    return form.getRawValue() as IAnchorTraderPartner | NewAnchorTraderPartner;
  }

  resetForm(form: AnchorTraderPartnerFormGroup, anchorTraderPartner: AnchorTraderPartnerFormGroupInput): void {
    const anchorTraderPartnerRawValue = { ...this.getFormDefaults(), ...anchorTraderPartner };
    form.reset(
      {
        ...anchorTraderPartnerRawValue,
        id: { value: anchorTraderPartnerRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AnchorTraderPartnerFormDefaults {
    return {
      id: null,
    };
  }
}

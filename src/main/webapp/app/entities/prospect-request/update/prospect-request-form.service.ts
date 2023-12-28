import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IProspectRequest, NewProspectRequest } from '../prospect-request.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IProspectRequest for edit and NewProspectRequestFormGroupInput for create.
 */
type ProspectRequestFormGroupInput = IProspectRequest | PartialWithRequiredKeyOf<NewProspectRequest>;

type ProspectRequestFormDefaults = Pick<NewProspectRequest, 'id'>;

type ProspectRequestFormGroupContent = {
  id: FormControl<IProspectRequest['id'] | NewProspectRequest['id']>;
  prospectRequestId: FormControl<IProspectRequest['prospectRequestId']>;
  anchorTraderId: FormControl<IProspectRequest['anchorTraderId']>;
  requestAmount: FormControl<IProspectRequest['requestAmount']>;
  prospectRequestDate: FormControl<IProspectRequest['prospectRequestDate']>;
  currency: FormControl<IProspectRequest['currency']>;
  financerequest: FormControl<IProspectRequest['financerequest']>;
};

export type ProspectRequestFormGroup = FormGroup<ProspectRequestFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ProspectRequestFormService {
  createProspectRequestFormGroup(prospectRequest: ProspectRequestFormGroupInput = { id: null }): ProspectRequestFormGroup {
    const prospectRequestRawValue = {
      ...this.getFormDefaults(),
      ...prospectRequest,
    };
    return new FormGroup<ProspectRequestFormGroupContent>({
      id: new FormControl(
        { value: prospectRequestRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      prospectRequestId: new FormControl(prospectRequestRawValue.prospectRequestId, {
        validators: [Validators.required],
      }),
      anchorTraderId: new FormControl(prospectRequestRawValue.anchorTraderId, {
        validators: [Validators.required],
      }),
      requestAmount: new FormControl(prospectRequestRawValue.requestAmount, {
        validators: [Validators.required],
      }),
      prospectRequestDate: new FormControl(prospectRequestRawValue.prospectRequestDate, {
        validators: [Validators.required],
      }),
      currency: new FormControl(prospectRequestRawValue.currency, {
        validators: [Validators.required],
      }),
      financerequest: new FormControl(prospectRequestRawValue.financerequest),
    });
  }

  getProspectRequest(form: ProspectRequestFormGroup): IProspectRequest | NewProspectRequest {
    return form.getRawValue() as IProspectRequest | NewProspectRequest;
  }

  resetForm(form: ProspectRequestFormGroup, prospectRequest: ProspectRequestFormGroupInput): void {
    const prospectRequestRawValue = { ...this.getFormDefaults(), ...prospectRequest };
    form.reset(
      {
        ...prospectRequestRawValue,
        id: { value: prospectRequestRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ProspectRequestFormDefaults {
    return {
      id: null,
    };
  }
}

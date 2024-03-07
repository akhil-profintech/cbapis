import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IEscrowAccountDetails, NewEscrowAccountDetails } from '../escrow-account-details.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEscrowAccountDetails for edit and NewEscrowAccountDetailsFormGroupInput for create.
 */
type EscrowAccountDetailsFormGroupInput = IEscrowAccountDetails | PartialWithRequiredKeyOf<NewEscrowAccountDetails>;

type EscrowAccountDetailsFormDefaults = Pick<NewEscrowAccountDetails, 'id'>;

type EscrowAccountDetailsFormGroupContent = {
  id: FormControl<IEscrowAccountDetails['id'] | NewEscrowAccountDetails['id']>;
  escrowDetailsId: FormControl<IEscrowAccountDetails['escrowDetailsId']>;
  escrowDetailsUlidId: FormControl<IEscrowAccountDetails['escrowDetailsUlidId']>;
  tenantId: FormControl<IEscrowAccountDetails['tenantId']>;
  customerId: FormControl<IEscrowAccountDetails['customerId']>;
  accName: FormControl<IEscrowAccountDetails['accName']>;
  ifscCode: FormControl<IEscrowAccountDetails['ifscCode']>;
  virtualAccNumber: FormControl<IEscrowAccountDetails['virtualAccNumber']>;
  poolingAccNumber: FormControl<IEscrowAccountDetails['poolingAccNumber']>;
};

export type EscrowAccountDetailsFormGroup = FormGroup<EscrowAccountDetailsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EscrowAccountDetailsFormService {
  createEscrowAccountDetailsFormGroup(
    escrowAccountDetails: EscrowAccountDetailsFormGroupInput = { id: null },
  ): EscrowAccountDetailsFormGroup {
    const escrowAccountDetailsRawValue = {
      ...this.getFormDefaults(),
      ...escrowAccountDetails,
    };
    return new FormGroup<EscrowAccountDetailsFormGroupContent>({
      id: new FormControl(
        { value: escrowAccountDetailsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      escrowDetailsId: new FormControl(escrowAccountDetailsRawValue.escrowDetailsId),
      escrowDetailsUlidId: new FormControl(escrowAccountDetailsRawValue.escrowDetailsUlidId),
      tenantId: new FormControl(escrowAccountDetailsRawValue.tenantId, {
        validators: [Validators.required],
      }),
      customerId: new FormControl(escrowAccountDetailsRawValue.customerId, {
        validators: [Validators.required],
      }),
      accName: new FormControl(escrowAccountDetailsRawValue.accName, {
        validators: [Validators.required],
      }),
      ifscCode: new FormControl(escrowAccountDetailsRawValue.ifscCode, {
        validators: [Validators.required],
      }),
      virtualAccNumber: new FormControl(escrowAccountDetailsRawValue.virtualAccNumber, {
        validators: [Validators.required],
      }),
      poolingAccNumber: new FormControl(escrowAccountDetailsRawValue.poolingAccNumber, {
        validators: [Validators.required],
      }),
    });
  }

  getEscrowAccountDetails(form: EscrowAccountDetailsFormGroup): IEscrowAccountDetails | NewEscrowAccountDetails {
    return form.getRawValue() as IEscrowAccountDetails | NewEscrowAccountDetails;
  }

  resetForm(form: EscrowAccountDetailsFormGroup, escrowAccountDetails: EscrowAccountDetailsFormGroupInput): void {
    const escrowAccountDetailsRawValue = { ...this.getFormDefaults(), ...escrowAccountDetails };
    form.reset(
      {
        ...escrowAccountDetailsRawValue,
        id: { value: escrowAccountDetailsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): EscrowAccountDetailsFormDefaults {
    return {
      id: null,
    };
  }
}

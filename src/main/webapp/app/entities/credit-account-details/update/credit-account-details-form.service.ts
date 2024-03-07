import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICreditAccountDetails, NewCreditAccountDetails } from '../credit-account-details.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICreditAccountDetails for edit and NewCreditAccountDetailsFormGroupInput for create.
 */
type CreditAccountDetailsFormGroupInput = ICreditAccountDetails | PartialWithRequiredKeyOf<NewCreditAccountDetails>;

type CreditAccountDetailsFormDefaults = Pick<NewCreditAccountDetails, 'id'>;

type CreditAccountDetailsFormGroupContent = {
  id: FormControl<ICreditAccountDetails['id'] | NewCreditAccountDetails['id']>;
  creditAccDetailsId: FormControl<ICreditAccountDetails['creditAccDetailsId']>;
  creditAccountDetailsUlidId: FormControl<ICreditAccountDetails['creditAccountDetailsUlidId']>;
  tenantId: FormControl<ICreditAccountDetails['tenantId']>;
  customerId: FormControl<ICreditAccountDetails['customerId']>;
  accName: FormControl<ICreditAccountDetails['accName']>;
  ifscCode: FormControl<ICreditAccountDetails['ifscCode']>;
  accNumber: FormControl<ICreditAccountDetails['accNumber']>;
  destinationAccountName: FormControl<ICreditAccountDetails['destinationAccountName']>;
  destinationAccountNumber: FormControl<ICreditAccountDetails['destinationAccountNumber']>;
  disbursement: FormControl<ICreditAccountDetails['disbursement']>;
  repayment: FormControl<ICreditAccountDetails['repayment']>;
};

export type CreditAccountDetailsFormGroup = FormGroup<CreditAccountDetailsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CreditAccountDetailsFormService {
  createCreditAccountDetailsFormGroup(
    creditAccountDetails: CreditAccountDetailsFormGroupInput = { id: null },
  ): CreditAccountDetailsFormGroup {
    const creditAccountDetailsRawValue = {
      ...this.getFormDefaults(),
      ...creditAccountDetails,
    };
    return new FormGroup<CreditAccountDetailsFormGroupContent>({
      id: new FormControl(
        { value: creditAccountDetailsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      creditAccDetailsId: new FormControl(creditAccountDetailsRawValue.creditAccDetailsId),
      creditAccountDetailsUlidId: new FormControl(creditAccountDetailsRawValue.creditAccountDetailsUlidId),
      tenantId: new FormControl(creditAccountDetailsRawValue.tenantId, {
        validators: [Validators.required],
      }),
      customerId: new FormControl(creditAccountDetailsRawValue.customerId, {
        validators: [Validators.required],
      }),
      accName: new FormControl(creditAccountDetailsRawValue.accName, {
        validators: [Validators.required],
      }),
      ifscCode: new FormControl(creditAccountDetailsRawValue.ifscCode, {
        validators: [Validators.required],
      }),
      accNumber: new FormControl(creditAccountDetailsRawValue.accNumber, {
        validators: [Validators.required],
      }),
      destinationAccountName: new FormControl(creditAccountDetailsRawValue.destinationAccountName),
      destinationAccountNumber: new FormControl(creditAccountDetailsRawValue.destinationAccountNumber),
      disbursement: new FormControl(creditAccountDetailsRawValue.disbursement),
      repayment: new FormControl(creditAccountDetailsRawValue.repayment),
    });
  }

  getCreditAccountDetails(form: CreditAccountDetailsFormGroup): ICreditAccountDetails | NewCreditAccountDetails {
    return form.getRawValue() as ICreditAccountDetails | NewCreditAccountDetails;
  }

  resetForm(form: CreditAccountDetailsFormGroup, creditAccountDetails: CreditAccountDetailsFormGroupInput): void {
    const creditAccountDetailsRawValue = { ...this.getFormDefaults(), ...creditAccountDetails };
    form.reset(
      {
        ...creditAccountDetailsRawValue,
        id: { value: creditAccountDetailsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CreditAccountDetailsFormDefaults {
    return {
      id: null,
    };
  }
}

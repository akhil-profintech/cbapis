import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IUserDtls, NewUserDtls } from '../user-dtls.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IUserDtls for edit and NewUserDtlsFormGroupInput for create.
 */
type UserDtlsFormGroupInput = IUserDtls | PartialWithRequiredKeyOf<NewUserDtls>;

type UserDtlsFormDefaults = Pick<NewUserDtls, 'id' | 'isAnchorTraderEnabled' | 'isTradePartnerEnabled' | 'isFinancePartnerEnabled'>;

type UserDtlsFormGroupContent = {
  id: FormControl<IUserDtls['id'] | NewUserDtls['id']>;
  userUlidId: FormControl<IUserDtls['userUlidId']>;
  userName: FormControl<IUserDtls['userName']>;
  tenantId: FormControl<IUserDtls['tenantId']>;
  isAnchorTraderEnabled: FormControl<IUserDtls['isAnchorTraderEnabled']>;
  anchorTraderId: FormControl<IUserDtls['anchorTraderId']>;
  isTradePartnerEnabled: FormControl<IUserDtls['isTradePartnerEnabled']>;
  tradePartnerId: FormControl<IUserDtls['tradePartnerId']>;
  isFinancePartnerEnabled: FormControl<IUserDtls['isFinancePartnerEnabled']>;
  financePartnerId: FormControl<IUserDtls['financePartnerId']>;
  defaultPersona: FormControl<IUserDtls['defaultPersona']>;
};

export type UserDtlsFormGroup = FormGroup<UserDtlsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class UserDtlsFormService {
  createUserDtlsFormGroup(userDtls: UserDtlsFormGroupInput = { id: null }): UserDtlsFormGroup {
    const userDtlsRawValue = {
      ...this.getFormDefaults(),
      ...userDtls,
    };
    return new FormGroup<UserDtlsFormGroupContent>({
      id: new FormControl(
        { value: userDtlsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      userUlidId: new FormControl(userDtlsRawValue.userUlidId),
      userName: new FormControl(userDtlsRawValue.userName),
      tenantId: new FormControl(userDtlsRawValue.tenantId),
      isAnchorTraderEnabled: new FormControl(userDtlsRawValue.isAnchorTraderEnabled),
      anchorTraderId: new FormControl(userDtlsRawValue.anchorTraderId),
      isTradePartnerEnabled: new FormControl(userDtlsRawValue.isTradePartnerEnabled),
      tradePartnerId: new FormControl(userDtlsRawValue.tradePartnerId),
      isFinancePartnerEnabled: new FormControl(userDtlsRawValue.isFinancePartnerEnabled),
      financePartnerId: new FormControl(userDtlsRawValue.financePartnerId),
      defaultPersona: new FormControl(userDtlsRawValue.defaultPersona),
    });
  }

  getUserDtls(form: UserDtlsFormGroup): IUserDtls | NewUserDtls {
    return form.getRawValue() as IUserDtls | NewUserDtls;
  }

  resetForm(form: UserDtlsFormGroup, userDtls: UserDtlsFormGroupInput): void {
    const userDtlsRawValue = { ...this.getFormDefaults(), ...userDtls };
    form.reset(
      {
        ...userDtlsRawValue,
        id: { value: userDtlsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): UserDtlsFormDefaults {
    return {
      id: null,
      isAnchorTraderEnabled: false,
      isTradePartnerEnabled: false,
      isFinancePartnerEnabled: false,
    };
  }
}

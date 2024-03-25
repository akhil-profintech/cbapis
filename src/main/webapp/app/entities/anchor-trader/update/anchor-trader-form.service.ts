import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAnchorTrader, NewAnchorTrader } from '../anchor-trader.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAnchorTrader for edit and NewAnchorTraderFormGroupInput for create.
 */
type AnchorTraderFormGroupInput = IAnchorTrader | PartialWithRequiredKeyOf<NewAnchorTrader>;

type AnchorTraderFormDefaults = Pick<
  NewAnchorTrader,
  | 'id'
  | 'erpAccess'
  | 'acceptTerms'
  | 'acceptDeclaration'
  | 'gstRegistrationCertificateUploadStatus'
  | 'gstRegistrationCertificateVerificationStatus'
  | 'udhyamRegistrationcertificateUploadStatus'
  | 'udhyamRegistrationcertificateVerificationStatus'
  | 'kycDeclaration'
>;

type AnchorTraderFormGroupContent = {
  id: FormControl<IAnchorTrader['id'] | NewAnchorTrader['id']>;
  atId: FormControl<IAnchorTrader['atId']>;
  atUlidId: FormControl<IAnchorTrader['atUlidId']>;
  orgId: FormControl<IAnchorTrader['orgId']>;
  tenantId: FormControl<IAnchorTrader['tenantId']>;
  customerName: FormControl<IAnchorTrader['customerName']>;
  orgName: FormControl<IAnchorTrader['orgName']>;
  gstId: FormControl<IAnchorTrader['gstId']>;
  phoneNumber: FormControl<IAnchorTrader['phoneNumber']>;
  anchorTraderName: FormControl<IAnchorTrader['anchorTraderName']>;
  location: FormControl<IAnchorTrader['location']>;
  anchorTraderGST: FormControl<IAnchorTrader['anchorTraderGST']>;
  anchorTraderSector: FormControl<IAnchorTrader['anchorTraderSector']>;
  anchorTraderPAN: FormControl<IAnchorTrader['anchorTraderPAN']>;
  kycCompleted: FormControl<IAnchorTrader['kycCompleted']>;
  bankDetails: FormControl<IAnchorTrader['bankDetails']>;
  emailId: FormControl<IAnchorTrader['emailId']>;
  personalEmailId: FormControl<IAnchorTrader['personalEmailId']>;
  accountNumber: FormControl<IAnchorTrader['accountNumber']>;
  ifscCode: FormControl<IAnchorTrader['ifscCode']>;
  bankName: FormControl<IAnchorTrader['bankName']>;
  branchName: FormControl<IAnchorTrader['branchName']>;
  erpAccess: FormControl<IAnchorTrader['erpAccess']>;
  typeOfFirm: FormControl<IAnchorTrader['typeOfFirm']>;
  panStatus: FormControl<IAnchorTrader['panStatus']>;
  tosDocument: FormControl<IAnchorTrader['tosDocument']>;
  acceptTerms: FormControl<IAnchorTrader['acceptTerms']>;
  acceptDeclaration: FormControl<IAnchorTrader['acceptDeclaration']>;
  gstRegistrationCertificateUploadStatus: FormControl<IAnchorTrader['gstRegistrationCertificateUploadStatus']>;
  gstRegistrationCertificateVerificationStatus: FormControl<IAnchorTrader['gstRegistrationCertificateVerificationStatus']>;
  udhyamRegistrationcertificateUploadStatus: FormControl<IAnchorTrader['udhyamRegistrationcertificateUploadStatus']>;
  udhyamRegistrationcertificateVerificationStatus: FormControl<IAnchorTrader['udhyamRegistrationcertificateVerificationStatus']>;
  kycDeclaration: FormControl<IAnchorTrader['kycDeclaration']>;
};

export type AnchorTraderFormGroup = FormGroup<AnchorTraderFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AnchorTraderFormService {
  createAnchorTraderFormGroup(anchorTrader: AnchorTraderFormGroupInput = { id: null }): AnchorTraderFormGroup {
    const anchorTraderRawValue = {
      ...this.getFormDefaults(),
      ...anchorTrader,
    };
    return new FormGroup<AnchorTraderFormGroupContent>({
      id: new FormControl(
        { value: anchorTraderRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      atId: new FormControl(anchorTraderRawValue.atId),
      atUlidId: new FormControl(anchorTraderRawValue.atUlidId),
      orgId: new FormControl(anchorTraderRawValue.orgId, {
        validators: [Validators.required],
      }),
      tenantId: new FormControl(anchorTraderRawValue.tenantId, {
        validators: [Validators.required],
      }),
      customerName: new FormControl(anchorTraderRawValue.customerName, {
        validators: [Validators.required],
      }),
      orgName: new FormControl(anchorTraderRawValue.orgName, {
        validators: [Validators.required],
      }),
      gstId: new FormControl(anchorTraderRawValue.gstId, {
        validators: [Validators.required],
      }),
      phoneNumber: new FormControl(anchorTraderRawValue.phoneNumber, {
        validators: [Validators.required],
      }),
      anchorTraderName: new FormControl(anchorTraderRawValue.anchorTraderName),
      location: new FormControl(anchorTraderRawValue.location),
      anchorTraderGST: new FormControl(anchorTraderRawValue.anchorTraderGST),
      anchorTraderSector: new FormControl(anchorTraderRawValue.anchorTraderSector),
      anchorTraderPAN: new FormControl(anchorTraderRawValue.anchorTraderPAN),
      kycCompleted: new FormControl(anchorTraderRawValue.kycCompleted),
      bankDetails: new FormControl(anchorTraderRawValue.bankDetails),
      emailId: new FormControl(anchorTraderRawValue.emailId),
      personalEmailId: new FormControl(anchorTraderRawValue.personalEmailId),
      accountNumber: new FormControl(anchorTraderRawValue.accountNumber),
      ifscCode: new FormControl(anchorTraderRawValue.ifscCode),
      bankName: new FormControl(anchorTraderRawValue.bankName),
      branchName: new FormControl(anchorTraderRawValue.branchName),
      erpAccess: new FormControl(anchorTraderRawValue.erpAccess),
      typeOfFirm: new FormControl(anchorTraderRawValue.typeOfFirm),
      panStatus: new FormControl(anchorTraderRawValue.panStatus),
      tosDocument: new FormControl(anchorTraderRawValue.tosDocument),
      acceptTerms: new FormControl(anchorTraderRawValue.acceptTerms),
      acceptDeclaration: new FormControl(anchorTraderRawValue.acceptDeclaration),
      gstRegistrationCertificateUploadStatus: new FormControl(anchorTraderRawValue.gstRegistrationCertificateUploadStatus),
      gstRegistrationCertificateVerificationStatus: new FormControl(anchorTraderRawValue.gstRegistrationCertificateVerificationStatus),
      udhyamRegistrationcertificateUploadStatus: new FormControl(anchorTraderRawValue.udhyamRegistrationcertificateUploadStatus),
      udhyamRegistrationcertificateVerificationStatus: new FormControl(
        anchorTraderRawValue.udhyamRegistrationcertificateVerificationStatus,
      ),
      kycDeclaration: new FormControl(anchorTraderRawValue.kycDeclaration),
    });
  }

  getAnchorTrader(form: AnchorTraderFormGroup): IAnchorTrader | NewAnchorTrader {
    return form.getRawValue() as IAnchorTrader | NewAnchorTrader;
  }

  resetForm(form: AnchorTraderFormGroup, anchorTrader: AnchorTraderFormGroupInput): void {
    const anchorTraderRawValue = { ...this.getFormDefaults(), ...anchorTrader };
    form.reset(
      {
        ...anchorTraderRawValue,
        id: { value: anchorTraderRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AnchorTraderFormDefaults {
    return {
      id: null,
      erpAccess: false,
      acceptTerms: false,
      acceptDeclaration: false,
      gstRegistrationCertificateUploadStatus: false,
      gstRegistrationCertificateVerificationStatus: false,
      udhyamRegistrationcertificateUploadStatus: false,
      udhyamRegistrationcertificateVerificationStatus: false,
      kycDeclaration: false,
    };
  }
}

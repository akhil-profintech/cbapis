import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FundsTransferTransactionDetailsDetailComponent } from './funds-transfer-transaction-details-detail.component';

describe('FundsTransferTransactionDetails Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FundsTransferTransactionDetailsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FundsTransferTransactionDetailsDetailComponent,
              resolve: { fundsTransferTransactionDetails: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FundsTransferTransactionDetailsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load fundsTransferTransactionDetails on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FundsTransferTransactionDetailsDetailComponent);

      // THEN
      expect(instance.fundsTransferTransactionDetails).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { EscrowTransactionDetailsDetailComponent } from './escrow-transaction-details-detail.component';

describe('EscrowTransactionDetails Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EscrowTransactionDetailsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: EscrowTransactionDetailsDetailComponent,
              resolve: { escrowTransactionDetails: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(EscrowTransactionDetailsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load escrowTransactionDetails on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', EscrowTransactionDetailsDetailComponent);

      // THEN
      expect(instance.escrowTransactionDetails).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
